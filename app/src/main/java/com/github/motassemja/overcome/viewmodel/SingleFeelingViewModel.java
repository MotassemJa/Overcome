package com.github.motassemja.overcome.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.github.motassemja.overcome.model.Feeling;

/**
 * Copyright github.com/MotassemJa on 5/15/2018.
 */
public class SingleFeelingViewModel extends ViewModel {

    private MutableLiveData<Feeling> mFeeling;

    public MutableLiveData<Feeling> getFeeling() {
        if (mFeeling == null) {
            mFeeling = new MutableLiveData<>();
        }
        return mFeeling;
    }
}
