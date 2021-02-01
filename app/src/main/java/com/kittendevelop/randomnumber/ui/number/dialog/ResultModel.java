package com.kittendevelop.randomnumber.ui.number.dialog;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;

import javax.inject.Inject;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class ResultModel {

    private EntityGeneratedItem mItem;
    private StringBuilder mDate;
    private StringBuilder mTime;

    @Inject
    public ResultModel() {
        mDate = new StringBuilder();
        mTime = new StringBuilder();
    }

    public void setResult(EntityGeneratedItem item){
        mItem = item;
        createDate();
    }

    public EntityGeneratedItem getItem(){
        return mItem;
    }

    public String lastResult(){
        if(mItem.getNumber()==Long.MIN_VALUE)return "ERROR";
        else return mItem.getValue();
    }

    public int idApply(){
        return R.string.add_to_ex;
    }

    public int idMassage(){
        return R.string.massage_report;
    }

    public int idSource(){
        if(mItem.getNumber()==Long.MIN_VALUE) {
            return R.string.result_report_error_1;
        }else if(mItem.getNumber()==Long.MAX_VALUE){
            return R.string.result_report_error_2;
        }else {
            if (mItem.getSource() == EntityGeneratedItem.SOURCE_NET) return R.string.source_net;
            else return R.string.source_app;
        }
    }

    public String getDate() {
        return mDate.toString();
    }

    public String getTime() {
        return mTime.toString();
    }

    private void createDate(){
        mDate.append(mItem.getDay()<10?"0"+mItem.getDay():mItem.getDay());
        mDate.append(":");
        mDate.append(mItem.getMonth()<10?"0"+mItem.getMonth():mItem.getMonth());
        mDate.append(":");
        mDate.append(mItem.getYear());
        createTime();
    }

    private void createTime(){

        mTime.append(mItem.getHour()<10?"0"+mItem.getHour():mItem.getHour());
        mTime.append(":");
        mTime.append(mItem.getMinute()<10?"0"+mItem.getMinute():mItem.getMinute());
        mTime.append(":");
        mTime.append(mItem.getSecond()<10?"0"+mItem.getSecond():mItem.getSecond());
    }

}
