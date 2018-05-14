package com.github.motassemja.overcome.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.github.motassemja.overcome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright github.com/MotassemJa on 5/14/2018.
 */
public class AddFeelingFragment extends Fragment {

    @BindView(R.id.et_feeling_name)
    TextInputEditText mEtFeelingName;

    @BindView(R.id.img_new_feeling)
    ImageView mImgFeeling;

    @BindView(R.id.btn_add_img)
    Button mBtnAddImage;

    public AddFeelingFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_feeling, container, false);

        ButterKnife.bind(this, view);

        return view;
    }
}
