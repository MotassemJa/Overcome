package com.github.motassemja.overcome.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
 * Copyright github.com/MotassemJa on 4/21/2018.
 */
public class ParentControlPanelFragment extends Fragment {

    public interface ParentControlPanelInteractor {
        void onFabClicked();
    }

    public static final String TAG = ParentControlPanelFragment.class.getSimpleName();

    @BindView(R.id.rv_feelings)
    RecyclerView mRvFeelings;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private FeelingsAdapter mAdapter;

    private FeelingViewModel mFeelingViewModel;

    private ParentControlPanelInteractor mListener;

    public ParentControlPanelFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (ParentControlPanelInteractor) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Must implement listener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent_control_panel, container, false);

        ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_parent_control_panel);

        mAdapter = new FeelingsAdapter(getActivity());

        mFeelingViewModel = ViewModelProviders.of(getActivity()).get(FeelingViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRvFeelings.setAdapter(mAdapter);
        mRvFeelings.setLayoutManager(new LinearLayoutManager(getActivity()));

        mFeelingViewModel.getAllFeelings().observe(this, feelingList -> mAdapter.setFeelings(feelingList));

        mFab.setOnClickListener(view1 -> {
            // mFeelingViewModel.insert(new Feeling("Test"), new AppExecutors());
            if (mListener != null) mListener.onFabClicked();
        });
    }
}
