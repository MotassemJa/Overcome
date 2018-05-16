package com.github.motassemja.overcome.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.motassemja.overcome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright github.com/MotassemJa on 4/2/2018.
 */
public class ChooseLevelFragment extends Fragment {

    public interface ChooseLevelInteractor {
        void onFirstLevelChosen();

        void onSecondLevelChosen();

        void onThirdLevelChosen();
    }

    @BindView(R.id.btnFirstLevel)
    Button mBtnFirstLevel;

    @BindView(R.id.btnSecondLevel)
    Button mBtnSecondLevel;

    @BindView(R.id.btnThirdLevel)
    Button mBtnThirdLevel;

    private ChooseLevelInteractor mListener;

    public ChooseLevelFragment() {
        // Required constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ChooseLevelInteractor) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Must implement listener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_level, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mBtnFirstLevel.setOnClickListener(view1 -> {
            if (mListener != null) mListener.onFirstLevelChosen();
        });

        mBtnSecondLevel.setOnClickListener(view2 -> {
            if (mListener != null) mListener.onSecondLevelChosen();
        });

        mBtnThirdLevel.setOnClickListener(view3 -> {
            if (mListener != null) mListener.onThirdLevelChosen();
        });
    }
}
