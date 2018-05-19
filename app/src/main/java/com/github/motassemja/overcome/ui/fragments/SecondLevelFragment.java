package com.github.motassemja.overcome.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.BitmapFactory;
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
import com.github.motassemja.overcome.model.Feeling;
import com.github.motassemja.overcome.viewmodel.FeelingViewModel;

import java.util.List;

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

    private FeelingViewModel mFeelingViewModel;

    public SecondLevelFragment() {
        // Required Public Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_level, container, false);

        ButterKnife.bind(this, view);

        mFeelingViewModel = ViewModelProviders.of(getActivity()).get(FeelingViewModel.class);

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

        mFeelingViewModel.getAllFeelings().observe(this, new Observer<List<Feeling>>() {
            @Override
            public void onChanged(@Nullable List<Feeling> feelings) {
                // FIXME random + check for nulls
                if (feelings != null && feelings.size() > 1) {
                    Feeling first = feelings.get(0);
                    mTvFeelingName.setText(first.getFeelingName());
                    mRbSecondChoice.setText(first.getFeelingName());
                    mImgSecondChoice.setImageBitmap(BitmapFactory.decodeByteArray(first.getFeelingImage(), 0, first.getFeelingImage().length));

                    Feeling second = feelings.get(1);
                    mRbFirstChoice.setText(second.getFeelingName());
                    mImgFirstChoice.setImageBitmap(BitmapFactory.decodeByteArray(second.getFeelingImage(), 0, second.getFeelingImage().length));
                }
            }
        });


    }
}
