package com.github.motassemja.overcome.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.motassemja.overcome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright github.com/MotassemJa on 5/19/2018.
 */
public class SecondLevelFragment extends Fragment {

    @BindView(R.id.tv_feeling_to_find)
    TextView mTvFeelingName;

    @BindView(R.id.img_choice_1)
    ImageView mImgFirstChoice;

    @BindView(R.id.img_choice_2)
    ImageView mImgSecondChoice;

    @BindView(R.id.rb_choice_1)
    RadioButton mRbFirstChoice;

    @BindView(R.id.rb_choice_2)
    RadioButton mRbSecondChoice;

    public SecondLevelFragment() {
        // Required Public Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_level, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRbFirstChoice.setOnClickListener(view1 -> {
            mRbSecondChoice.setChecked(!mRbFirstChoice.isChecked());
        });

        mRbSecondChoice.setOnClickListener(view12 -> {
            mRbFirstChoice.setChecked(!mRbSecondChoice.isChecked());
        });
    }
}
