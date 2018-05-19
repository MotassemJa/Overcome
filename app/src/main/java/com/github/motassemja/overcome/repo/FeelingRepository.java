package com.github.motassemja.overcome.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.github.motassemja.overcome.AppExecutors;
import com.github.motassemja.overcome.db.FeelingsDatabase;
import com.github.motassemja.overcome.db.dao.FeelingDao;
import com.github.motassemja.overcome.model.Feeling;

import java.util.List;

/**
 * Copyright github.com/MotassemJa on 5/11/2018.
 */
public class FeelingRepository {

    private FeelingDao mFeelingDao;

    private LiveData<List<Feeling>> mFeelingsList;

    public FeelingRepository(Application application) {
        FeelingsDatabase db = FeelingsDatabase.getInstance(application, new AppExecutors());

        mFeelingDao = db.feelingDao();

        mFeelingsList = mFeelingDao.getAllFeelings();

    }

    public void insert(Feeling feeling, AppExecutors executors) {
        executors.diskIO().execute(() -> mFeelingDao.insertFeeling(feeling));
    }

    public LiveData<List<Feeling>> getFeelingsList() {
        return mFeelingsList;
    }
}
