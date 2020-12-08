package com.kittendevelop.randomnumber.ui.number.adapters;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kittendevelop.randomnumber.R;

public abstract class NumberHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView mTitle;
    private FrameLayout mContainer;

    public NumberHolder(@NonNull View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.result_numb);
        mContainer = itemView.findViewById(R.id.result_container);
    }

    public TextView getTitle() {
        return mTitle;
    }

    public FrameLayout getContainer() {
        return mContainer;
    }
}
