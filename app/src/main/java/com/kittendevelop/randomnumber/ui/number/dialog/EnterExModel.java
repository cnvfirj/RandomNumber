package com.kittendevelop.randomnumber.ui.number.dialog;

import com.kittendevelop.randomnumber.R;

import javax.inject.Inject;

public class EnterExModel {


    private static String mValue;


    @Inject
    public EnterExModel() {
    }

    public void setValue(String v){
        mValue = v;
    }

    public String getValue(){
        if(mValue==null)mValue = "";
        return mValue;
    }
}
