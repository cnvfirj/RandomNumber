package com.kittendevelop.randomnumber.ui.number.dialog;

import com.kittendevelop.randomnumber.R;

import javax.inject.Inject;

public class ResultModel {

    private String mResult;

    @Inject
    public ResultModel() {
    }

    public void setResult(String result){
        mResult = result;
    }

    public String lastResult(){
        return mResult;
    }

    public int idApply(){
        return R.string.add_to_ex;
    }

}
