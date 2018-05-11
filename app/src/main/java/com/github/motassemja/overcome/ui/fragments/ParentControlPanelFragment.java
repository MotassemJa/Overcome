package com.github.motassemja.overcome.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.motassemja.overcome.R;

import butterknife.ButterKnife;

/**
 * Copyright github.com/MotassemJa on 4/21/2018.
 */
public class ParentControlPanelFragment extends Fragment {

    public static final String TAG = ParentControlPanelFragment.class.getSimpleName();

    public ParentControlPanelFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent_control_panel, container, false);

        ButterKnife.bind(this, view);

        return view;
    }
}
