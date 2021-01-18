package com.kittendevelop.randomnumber.ui.number.dialog;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityExItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;

import javax.inject.Inject;

public class ItemModel {

    private BaseEntityItems mItem;

    private String mTag;

    @Inject
    public ItemModel() {
    }

    public void setItem(BaseEntityItems item,String tag){
       mItem = item;
       mTag = tag;
    }

    public BaseEntityItems getItem(){
        return mItem;
    }

    public String getTag(){
        return mTag;
    }

    public String from(){
        if(mTag.equals(ReceiverItem.TAG_STORY)){
            return Long.toString(((EntityGeneratedItem)mItem).mFrom);
        }else return "";
    }

    public String to(){
        if(mTag.equals(ReceiverItem.TAG_STORY)){
            return Long.toString(((EntityGeneratedItem)mItem).mTo);
        }else return "";
    }

    public int source(){
        if (mTag.equals(ReceiverItem.TAG_STORY)) {
            if(mItem.mSource==EntityGeneratedItem.SOURCE_APP)return R.string.source_app;
            else return R.string.source_net;
        }else {
            if(mItem.mSource== EntityGeneratedEx.SOURCE_AUTO)return R.string.redact_report_result_enter;
            else return R.string.redact_report_manual_enter;
        }
    }

    public int getIcon(){
        if(mTag.equals(ReceiverItem.TAG_STORY)){
            return R.drawable.ic_add_ex;
        }else return R.drawable.ic_remove_ex;
    }

    public int getTint(){
        if(mTag.equals(ReceiverItem.TAG_STORY)){
            return R.color.purple_500;
        }else return R.color.willingness_no;
    }



    public void date(StringBuilder builder){
       builder.append(mItem.getDay()<10?"0"+mItem.getDay():mItem.getDay());
       builder.append(":");
       builder.append(mItem.getMonth()<10?"0"+mItem.getMonth():mItem.getMonth());
       builder.append(":");
       builder.append(mItem.mYear);
    }

    public void time(StringBuilder builder){
        builder.append(mItem.getHour()<10?"0"+mItem.getHour():mItem.getHour());
        builder.append(":");
        builder.append(mItem.getMinute()<10?"0"+mItem.getMinute():mItem.getMinute());
        builder.append(":");
        builder.append(mItem.getSecond()<10?"0"+mItem.getSecond():mItem.getSecond());
    }
}
