package com.kittendevelop.randomnumber.ui.number.dialog;

import android.annotation.SuppressLint;
import android.view.View;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.ui.number.ParentFragmentCallback;

import javax.inject.Inject;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class ResultPresenter {

    private DialogFeedback mView;

    private ResultModel mModel;

    private ParentFragmentCallback mCallback;

    @Inject
    public ResultPresenter(ResultModel model) {
        this.mModel = model;
    }

    public ResultPresenter setResult(String value){
            mModel.setResult(value);
            return this;
    }

    public ResultPresenter bindView(DialogFeedback view){
        mView = view;
        return this;
    }

    public String value(){
        return mModel.lastResult();
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
               mCallback = (ParentFragmentCallback)mView.fragment().getParentFragment();
               if(mCallback!=null)mCallback.addToEx(Long.parseLong(mModel.lastResult()),ReceiverResult.TAG);
               mView.exitDialog();
               break;
       }
    }

    public void destroy(){
        mView = null;
    }
}
