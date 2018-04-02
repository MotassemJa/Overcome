package com.github.motassemja.overcome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.github.motassemja.overcome.AppExecutors;
import com.github.motassemja.overcome.db.dao.FeelingDao;
import com.github.motassemja.overcome.model.Feeling;

import java.util.List;

/**
 * Copyright github.com/MotassemJa on 4/1/2018.
 */

@Database(entities = {Feeling.class}, version = 1)
public abstract class FeelingsDatabase extends RoomDatabase {

    private static FeelingsDatabase sInstance;

    @VisibleForTesting
    public static final String DB_NAME = "feelings-db";

    private final MutableLiveData<Boolean> mIsDbCreated = new MutableLiveData<>();

    public static FeelingsDatabase getInstance(final Context context, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (FeelingsDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static FeelingsDatabase buildDatabase(final Context appContext,
                                                  final AppExecutors executors) {
        return Room.databaseBuilder(appContext, FeelingsDatabase.class, DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            FeelingsDatabase database = FeelingsDatabase.getInstance(appContext, executors);
                            List<Feeling> products = DataGenerator.generateFeelings();

                            insertData(database, products);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DB_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDbCreated.postValue(true);
    }

    private static void insertData(final FeelingsDatabase database, final List<Feeling> feelings) {
        database.runInTransaction(() -> {
            database.feelingDao().insertAllFeelings(feelings);
        });
    }

    private static void addDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDbCreated;
    }

    public abstract FeelingDao feelingDao();

}
