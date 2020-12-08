package com.kittendevelop.randomnumber.ui.number.adapters;

import androidx.recyclerview.widget.DiffUtil;

public class DiffCallbackNumb extends DiffUtil.Callback   {

    @Override
    public int getOldListSize() {
        return 0;
    }

    @Override
    public int getNewListSize() {
        return 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
