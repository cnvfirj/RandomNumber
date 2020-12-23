package com.kittendevelop.randomnumber.ui.number.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;

public class EntityItemsAdapter extends PagedListAdapter<BaseEntityItems, EntityItemsAdapter.ItemHolder> {

    private int[]mColors;



    public EntityItemsAdapter(@NonNull DiffUtil.ItemCallback<BaseEntityItems> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return holder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
         holder.bind(position);
    }

    public EntityItemsAdapter setColors(int[]colors){
        mColors = colors;
        return this;
    }

    private ItemHolder holder(ViewGroup parent){
         ItemHolder holder = new ItemHolder(LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.item_number_list, parent, false));
         holder.setColors(mColors);
         return holder;
    }

    protected static class ItemHolder extends NumberHolder{

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
