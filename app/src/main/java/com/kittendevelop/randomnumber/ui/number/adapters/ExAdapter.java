package com.kittendevelop.randomnumber.ui.number.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kittendevelop.randomnumber.R;

public class ExAdapter extends RecyclerView.Adapter<ExAdapter.ExAdapterHolder>{

    private String[] mTitles;
    private int[]mColors;

    @NonNull
    @Override
    public ExAdapter.ExAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExAdapter.ExAdapterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_number_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExAdapter.ExAdapterHolder holder, int position) {
        if(position==0){
            holder.getTitle().setText("edit");
            Drawable d = holder.getContainer().getBackground();
            if(mColors==null)DrawableCompat.setTint(d, Color.RED);
            else DrawableCompat.setTint(d, mColors[0]);
            holder.getContainer().setBackground(d);
        }else {
            holder.getTitle().setText(mTitles[position-1]);
        }
    }

    public ExAdapter setColors(int[]colors){
        mColors = colors;
        return this;
    }
    public void setTitles(String[]titles){
        mTitles = titles;
    }

    @Override
    public int getItemCount() {
        if(mTitles!=null)return mTitles.length+1;
        else return 1;
    }

    protected static class ExAdapterHolder extends NumberHolder{

        public ExAdapterHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
