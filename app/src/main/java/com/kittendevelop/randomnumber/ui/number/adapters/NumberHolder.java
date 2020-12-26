package com.kittendevelop.randomnumber.ui.number.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kittendevelop.randomnumber.R;

public abstract class NumberHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView mTitle;


    public NumberHolder(@NonNull View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.result_numb);

    }

    protected TextView getTitle() {
        return mTitle;
    }

}
