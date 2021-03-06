package com.kittendevelop.randomnumber.ui.number.dialog;

import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;

import javax.inject.Inject;

public class ReceiverWaiting extends ReceiverDialogs{

    private static ReceiverWaiting single;

    @Inject
    public ReceiverWaiting(DialogWaiting mDialog) {
        super(mDialog);
    }

    public static ReceiverWaiting instance(){
        if(single==null){
            synchronized (ReceiverWaiting.class){
                single = DaggerComponentDialogs.create().waiting();
            }
        }
        return single;
    }

}
