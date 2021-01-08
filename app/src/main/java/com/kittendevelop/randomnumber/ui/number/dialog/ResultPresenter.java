package com.kittendevelop.randomnumber.ui.number.dialog;

import android.annotation.SuppressLint;
import android.view.View;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.ui.number.ParentFragmentCallback;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;

import javax.inject.Inject;

public class ResultPresenter {

    private DialogFeedback mView;

    private final ResultModel mModel;

    @Inject
    public ResultPresenter(ResultModel model) {
        this.mModel = model;
    }

    public ResultPresenter setResult(EntityGeneratedItem item){
        mModel.setResult(item);
        return this;
    }

    public void bindView(DialogFeedback view){
        mView = view;
    }

    public String value(){
        return mModel.lastResult();
    }

    public String report(){
        StringBuilder report = new StringBuilder();
        if(!value().equals("ERROR")) {
            String[] split = mView.context().getResources().getString(mModel.idMassage()).split("%%");
            report.append(split[0]);
            report.append(mModel.getDate());
            report.append(split[1]);
            report.append(mModel.getTime());
            report.append(split[2]);
            report.append(mModel.getItem().getFrom());
            report.append(split[3]);
            report.append(mModel.getItem().getTo());
            report.append(split[4]);
        }
            report.append(mView.context().getResources().getString(mModel.idSource()));
            return report.toString();

    }

    public String textApply(){
        return mView.context().getResources().getString(mModel.idApply());
    }

    @SuppressLint("NonConstantResourceId")
    public void click(View v){
       switch (v.getId()){
           case R.id.result_exit:
               mView.exitDialog();
               break;
           case R.id.result_aply:
               if(!mModel.lastResult().equals("ERROR")) {
                   ParentFragmentCallback mCallback = (ParentFragmentCallback) mView.fragment().getParentFragment();
                   if (mCallback != null)
                       mCallback.addToEx(Long.parseLong(mModel.lastResult()), ReceiverResult.TAG);
               }
               mView.exitDialog();
               break;
       }
    }

    public void destroy(){
        mView = null;
    }
}
