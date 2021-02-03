package com.kittendevelop.randomnumber.ui.number.dialog;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.ui.number.ParentFragmentCallback;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;

import javax.inject.Inject;

public class ItemPresenter {

    private ItemModel mModel;

    private DialogFeedback mView;

    private ParentFragmentCallback mParentCallback;

    @Inject
    public ItemPresenter(ItemModel mModel) {
        this.mModel = mModel;
    }

    public ItemPresenter setItem(BaseEntityItems item,String tag){
        mModel.setItem(item,tag);
        return this;
    }

    public ItemPresenter bindView(DialogFeedback view){
        mView = view;
        return this;
    }

    public Drawable iconApply(){
        return mView.context().getResources().getDrawable(mModel.getIcon(),null);
    }

    public int colorApply(){
        return mView.context().getResources().getColor(mModel.getTint(),null);
    }

    public String value(){
        return mModel.getItem().mValue;
    }

    public String report(){
        StringBuilder builder = new StringBuilder();
        if(mModel.getTag().equals(ReceiverItem.TAG_STORY)){
            return createReportStory(builder,(mView.context().getResources().getString(R.string.massage_report).split("%%")));
        }else {
            return createReportEx(builder,(mView.context().getResources().getString(R.string.redact_report_ex).split("%%")));
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void click(View v){
        switch (v.getId()){
            case R.id.item_exit:
                 mView.exitDialog();
                 break;
            case R.id.item_del:
                mParentCallback = ((ParentFragmentCallback)mView.fragment().getParentFragment());
                if(mParentCallback!=null)mParentCallback.delete(mModel.getItem(),mModel.getTag());
                mView.exitDialog();
                break;
            case R.id.item_clear:
                mParentCallback = ((ParentFragmentCallback)mView.fragment().getParentFragment());
                if(mParentCallback!=null)mParentCallback.clear(mModel.getTag());
                mView.exitDialog();
                break;
        }
    }

    public void destroy(){
      mView = null;
    }
    
    private String createReportEx(StringBuilder builder,String[] split){
        builder.append(split[0]);
        mModel.date(builder);
        builder.append(split[1]);
        mModel.time(builder);
        builder.append(" ").append(mView.context().getResources().getString(mModel.source()));
        return builder.toString();
    }
    
    private String createReportStory(StringBuilder builder,String[] split){
        builder.append(split[0]);
        mModel.date(builder);
        builder.append(split[1]);
        mModel.time(builder);
        builder.append(split[2]);
        builder.append(mModel.from());
        builder.append(split[3]);
        builder.append(mModel.to());
        builder.append(mView.context().getResources().getString(mModel.source()));
        return builder.toString();
    }
    
    

}
