package com.kittendevelop.randomnumber.ui.number.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.ItemNumberListBinding;
import com.kittendevelop.randomnumber.ui.number.PresenterNumb;
import com.kittendevelop.randomnumber.ui.number.db.CommonValues;

import java.util.List;

import javax.inject.Inject;

import static com.kittendevelop.randomnumber.help.Massages.ERROR;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ItemHolder> {

    private DiffUtilIItems mDiff;
    private List<CommonValues>mList;
    private PresenterNumb.ListenItems mListenItems;

    @Inject
    public AdapterList(DiffUtilIItems diff) {
        this.mDiff = diff;
    }

    public AdapterList list(List<CommonValues>list){
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(mDiff.lists(mList,list));
        result.dispatchUpdatesTo(this);
        mList = list;
        return this;
    }

    public void removeItem(CommonValues item){
        notifyItemRemoved(mList.indexOf(item));
    }

    public void removeItem(int pos){
        notifyItemRemoved(pos);
    }

    public AdapterList setListen(PresenterNumb.ListenItems listen){
        mListenItems = listen;
        return this;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return holder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
       holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
      if(mList!=null) return mList.size();
      else return 0;
    }

    private void clickItem(CommonValues item, int pos){
      if(mListenItems!=null)mListenItems.item(item,pos);
      else ERROR("adapter listen null");
    }

    private ItemHolder holder(ViewGroup parent){
        return new ItemHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_number_list, parent, false));
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
            clickItem(mBinding.getTitle(),getAdapterPosition());
        }
    }
}
