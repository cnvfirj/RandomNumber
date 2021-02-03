package com.kittendevelop.randomnumber.ui.number.dialog;

import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;

import javax.inject.Inject;

public class ReceiverEnterEx extends ReceiverDialogs{

    public static final String TAG = "EX";

    private static ReceiverEnterEx single;

    public static ReceiverEnterEx instance(){
        if(single==null){
            synchronized (ReceiverEnterEx.class){
                single = DaggerComponentDialogs.create().enterEx();
            }
        }
        return single;
    }
    @Inject
    public ReceiverEnterEx(DialogEnterEx mDialog) {
        super(mDialog);
    }
}
