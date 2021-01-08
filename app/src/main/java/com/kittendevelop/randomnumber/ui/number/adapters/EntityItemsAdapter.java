package com.kittendevelop.randomnumber.ui.number.adapters;

import android.app.LauncherActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.ItemNumberListBinding;
import com.kittendevelop.randomnumber.help.Massages;
import com.kittendevelop.randomnumber.ui.number.PresenterNumb;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.CommonValues;

import javax.inject.Inject;

public class EntityItemsAdapter extends PagedListAdapter<CommonValues, EntityItemsAdapter.ItemHolder> {

    private PresenterNumb.ListenItems mListenItems;

    @Inject
    public EntityItemsAdapter(@NonNull DiffUtil.ItemCallback<CommonValues> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return holder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
         holder.bind(getItem(position));
    }

    public void setListen(PresenterNumb.ListenItems listen){
        mListenItems = listen;
    }


    private ItemHolder holder(ViewGroup parent){
         return new ItemHolder(LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.item_number_list, parent, false));
    }

    private void clickItem(CommonValues item){
        if(mListenItems!=null)mListenItems.item(item);
    }

    protected class ItemHolder extends NumberHolder{

        private ItemNumberListBinding mBinding;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = ItemNumberListBinding.bind(itemView);
            mBinding.setClick(this);
        }


        public void bind(CommonValues item){
          mBinding.setTitle(item);
        }

        public CommonValues getItem() {
            return mBinding.getTitle();
        }

        @Override
        public void click() {

            clickItem(mBinding.getTitle());
        }
    }
}
