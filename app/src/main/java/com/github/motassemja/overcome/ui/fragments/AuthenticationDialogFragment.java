package com.github.motassemja.overcome.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.motassemja.overcome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright github.com/MotassemJa on 4/21/2018.
 */
public class AuthenticationDialogFragment extends DialogFragment {

    public static final String TAG = AuthenticationDialogFragment.class.getSimpleName();

    @BindView(R.id.et_username)
    TextInputEditText mEtUsername;

    @BindView(R.id.et_password)
    TextInputEditText mEtPassword;

    @BindView(R.id.btnLogin)
    AppCompatButton mBtnLogin;

    @BindView(R.id.btnCancel)
    AppCompatButton mBtnCancel;

    public AuthenticationDialogFragment() {
        // Required Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_authenticate, container, false);

        ButterKnife.bind(this, view);

        getDialog().setTitle(R.string.lbl_login);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mBtnCancel.setOnClickListener(view1 -> {
            dismiss();
        });
    }
}
