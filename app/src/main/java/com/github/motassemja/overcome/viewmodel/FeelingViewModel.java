package com.github.motassemja.overcome.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.github.motassemja.overcome.AppExecutors;
import com.github.motassemja.overcome.model.Feeling;
import com.github.motassemja.overcome.repo.FeelingRepository;

import java.util.List;

/**
 * Copyright github.com/MotassemJa on 5/11/2018.
 */
public class FeelingViewModel extends AndroidViewModel {

    private FeelingRepository mRepository;

    private LiveData<List<Feeling>> mAllFeelings;

    public FeelingViewModel(@NonNull Application application) {
        super(application);
        mRepository = new FeelingRepository(application);
        mAllFeelings = mRepository.getFeelingsList();
    }

    public LiveData<List<Feeling>> getAllFeelings() {
        return mAllFeelings;
    }

    public void insert(Feeling feeling, AppExecutors executors) {
        mRepository.insert(feeling, executors);
    }
}
