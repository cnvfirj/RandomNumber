package com.kittendevelop.randomnumber.ui.number.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.kittendevelop.randomnumber.ui.number.db.CommonValues;


/*delete*/
public class DiffCallbackNumb extends DiffUtil.ItemCallback<CommonValues>   {

    @Override
    public boolean areItemsTheSame(@NonNull CommonValues oldItem, @NonNull CommonValues newItem) {
        return oldItem.mNumber==newItem.mNumber;
    }
    @Override
    public boolean areContentsTheSame(@NonNull CommonValues oldItem, @NonNull CommonValues newItem) {
        return oldItem.mValue.equals(newItem.mValue);
    }
}
