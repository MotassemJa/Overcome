package com.github.motassemja.overcome;

import android.app.Application;

import com.github.motassemja.overcome.db.FeelingsDatabase;

/**
 * Copyright github.com/MotassemJa on 4/2/2018.
 */
public class App extends Application {

    private AppExecutors appExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        appExecutors = new AppExecutors();
    }

    public FeelingsDatabase getDatabase() {
        return FeelingsDatabase.getInstance(this, appExecutors);
    }

}
