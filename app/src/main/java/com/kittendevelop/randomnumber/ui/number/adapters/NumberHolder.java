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
    private FrameLayout mContainer;
    private int[] mColors;

    public NumberHolder(@NonNull View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.result_numb);
        mContainer = itemView.findViewById(R.id.result_container);
    }

    public void bind(int position){
        fillBackground(position);
    }

    public void setColors(int[]colors){
        mColors = colors;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public FrameLayout getContainer() {
        return mContainer;
    }

    private void fillBackground(int position){
        Drawable d = mContainer.getBackground();
        if(position==0){
            mTitle.setText("edit");
            if(mColors==null)DrawableCompat.setTint(d, Color.RED);
            else DrawableCompat.setTint(d, mColors[0]);
        }else {
            DrawableCompat.setTint(d, Color.WHITE);
        }
        mContainer.setBackground(d);
    }
}
