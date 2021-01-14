package com.kittendevelop.randomnumber.ui.number.adapters;

import androidx.recyclerview.widget.DiffUtil;

import com.kittendevelop.randomnumber.help.Massages;
import com.kittendevelop.randomnumber.ui.number.db.CommonValues;

import java.util.List;

public class DiffUtilIItems extends DiffUtil.Callback {

    private List<CommonValues>mOld;
    private List<CommonValues>mNew;



    public DiffUtilIItems lists(List<CommonValues>o, List<CommonValues>n){
        mNew = n;
        mOld = o;
        return this;
    }

    public DiffUtilIItems setOld(List<CommonValues>o){
        mOld = o;
        return this;
    }

    public DiffUtilIItems setNew(List<CommonValues>n){
        mNew = n;
        return this;
    }

    @Override
    public int getOldListSize() {
       if(mOld!=null) return mOld.size();
       else {
           Massages.ERROR("old list null");
           return 0;
       }
    }

    @Override
    public int getNewListSize() {
        if(mNew!=null) return mNew.size();
        else {
            Massages.ERROR("new list null");
            return 0;
        }
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if(mOld!=null&&mNew!=null){
            if(mOld.get(oldItemPosition)!=null&&mNew.get(newItemPosition)!=null)
                return mOld.get(oldItemPosition).mNumber==mNew.get(newItemPosition).mNumber;
        }
         return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if(mOld!=null&&mNew!=null){
            if(mOld.get(oldItemPosition)!=null&&mNew.get(newItemPosition)!=null)
                return mOld.get(oldItemPosition).mValue.equals(mNew.get(newItemPosition).mValue);
        }
         return false;
    }
}
