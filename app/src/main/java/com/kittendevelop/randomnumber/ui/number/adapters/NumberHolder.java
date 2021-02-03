package com.kittendevelop.randomnumber.ui.number.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kittendevelop.randomnumber.R;

public abstract class NumberHolder extends RecyclerView.ViewHolder{

    protected TextView mTitle;

    public NumberHolder(@NonNull View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.result_numb);
    }

    protected TextView getTitle() {
        return mTitle;
    }

    public abstract void click();

}
