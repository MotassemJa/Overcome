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

    public interface AuthenticationDialogFragmentListener {
        void onLoginButtonClicked();
    }

    public static final String TAG = AuthenticationDialogFragment.class.getSimpleName();

    private static final String TEST_USERNAME = "TEST";

    private static final String TEST_PASSWORD = "123";

    @BindView(R.id.et_username)
    TextInputEditText mEtUsername;

    @BindView(R.id.et_password)
    TextInputEditText mEtPassword;

    @BindView(R.id.btnLogin)
    AppCompatButton mBtnLogin;

    @BindView(R.id.btnCancel)
    AppCompatButton mBtnCancel;

    private AuthenticationDialogFragmentListener mListener;

    public AuthenticationDialogFragment() {
        // Required Constructor
    }

    private void setListener(AuthenticationDialogFragmentListener listener) {
        this.mListener = listener;
    }

    public static AuthenticationDialogFragment newInstance(@NonNull AuthenticationDialogFragmentListener listener) {
        AuthenticationDialogFragment fragment = new AuthenticationDialogFragment();
        fragment.setListener(listener);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogFragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_authenticate, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(R.string.lbl_login);
        mBtnCancel.setOnClickListener(view1 -> {
            dismiss();
        });

        mBtnLogin.setOnClickListener(view1 -> {
            String username = mEtUsername.getText().toString();
            String password = mEtPassword.getText().toString();

            if (username.equalsIgnoreCase(TEST_USERNAME) && password.equals(TEST_PASSWORD)) {
                dismiss();
                mListener.onLoginButtonClicked();
            }
        });
    }
}
