package com.github.motassemja.overcome.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.motassemja.overcome.R;
import com.github.motassemja.overcome.adapters.FeelingsAdapter;
import com.github.motassemja.overcome.viewmodel.FeelingViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright github.com/MotassemJa on 5/16/2018.
 */
public class FirstLevelFragment extends Fragment {

    @BindView(R.id.rv_first_lvl)
    RecyclerView mRvFeelings;

    private FeelingsAdapter mAdapter;

    private FeelingViewModel mFeelingViewModel;

    public FirstLevelFragment() {
        // Required constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_level, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new FeelingsAdapter(getActivity());

        mFeelingViewModel = ViewModelProviders.of(getActivity()).get(FeelingViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRvFeelings.setLayoutManager(layoutManager);
        mRvFeelings.setHasFixedSize(true);
        mRvFeelings.setAdapter(mAdapter);

        mFeelingViewModel.getAllFeelings().observe(this, feelingList -> mAdapter.setFeelings(feelingList));
    }
}
