package com.github.motassemja.overcome.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.motassemja.overcome.AppExecutors;
import com.github.motassemja.overcome.R;
import com.github.motassemja.overcome.model.Feeling;
import com.github.motassemja.overcome.viewmodel.FeelingViewModel;
import com.github.motassemja.overcome.viewmodel.SingleFeelingViewModel;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright github.com/MotassemJa on 5/14/2018.
 */
public class AddFeelingFragment extends Fragment {

    public interface AddFeelingInteractor {
        void onTakePictureButtonClicked();
    }

    @BindView(R.id.et_feeling_name)
    TextInputEditText mEtFeelingName;

    @BindView(R.id.img_new_feeling)
    AppCompatImageView mImgFeeling;

    @BindView(R.id.btn_add_img)
    Button mBtnAddImage;

    @BindView(R.id.btn_save_feeling)
    Button mBtnSave;

    private AddFeelingInteractor mListener;

    private SingleFeelingViewModel mSingleFeelingViewModel;

    private FeelingViewModel mFeelingViewModel;

    public AddFeelingFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (AddFeelingInteractor) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Must implement listener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_feeling, container, false);

        ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_add_new_feeling);

        mSingleFeelingViewModel = ViewModelProviders.of(getActivity()).get(SingleFeelingViewModel.class);

        mFeelingViewModel = ViewModelProviders.of(getActivity()).get(FeelingViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mBtnAddImage.setOnClickListener(view1 -> {
            if (mListener != null) mListener.onTakePictureButtonClicked();
        });


        mSingleFeelingViewModel.getFeeling().observe(this, feeling -> {
            if (feeling == null) return;
            byte[] byteArray = feeling.getFeelingImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                    byteArray.length);
            mImgFeeling.setImageBitmap(bitmap);
        });

        mBtnSave.setOnClickListener(view12 -> {
            String name = mEtFeelingName.getText().toString();
            Feeling feeling = new Feeling(name);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ((BitmapDrawable) mImgFeeling.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            feeling.setFeelingImage(byteArray);
            mFeelingViewModel.insert(feeling, new AppExecutors());
        });
    }
}
