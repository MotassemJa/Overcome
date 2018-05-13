package com.github.motassemja.overcome.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.motassemja.overcome.R;
import com.github.motassemja.overcome.model.Feeling;

import java.util.List;

/**
 * Copyright github.com/MotassemJa on 5/11/2018.
 */
public class FeelingsAdapter extends RecyclerView.Adapter {

    class FeelingViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvID, tvName;
        private Button btnEditImg;
        private AppCompatImageView imgFeeling;

        private FeelingViewHolder(View view) {
            super(view);
            tvID = view.findViewById(R.id.tv_feeling_id);
            tvName = view.findViewById(R.id.tv_feeling_name);
            btnEditImg = view.findViewById(R.id.btn_edit_img);
            imgFeeling = view.findViewById(R.id.img_feeling);
        }
    }

    private final LayoutInflater mLayoutInflater;

    private List<Feeling> mFeelings;

    public FeelingsAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_feeling, parent, false);
        return new FeelingViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FeelingViewHolder feelingViewHolder = (FeelingViewHolder) holder;
        if (mFeelings != null) {
            Feeling feeling = mFeelings.get(position);
            feelingViewHolder.tvName.setText(feeling.getFeelingName());
            feelingViewHolder.tvID.setText(String.valueOf(feeling.getFeelingID()));
        } else {
            feelingViewHolder.tvID.setText("N/A");
            feelingViewHolder.tvName.setText("N/A");
        }
    }

    @Override
    public int getItemCount() {
        if (mFeelings != null) return mFeelings.size();
        return 0;
    }

    public void setFeelings(List<Feeling> list) {
        mFeelings = list;
        notifyDataSetChanged();
    }
}
