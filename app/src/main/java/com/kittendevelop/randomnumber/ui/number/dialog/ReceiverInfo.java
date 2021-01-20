package com.kittendevelop.randomnumber.ui.number.dialog;


import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;

import javax.inject.Inject;

public class ReceiverInfo extends ReceiverDialogs{

    private static ReceiverInfo single;

    @Inject
    public ReceiverInfo(DialogInfo mDialog) {
        super(mDialog);
    }

    public static ReceiverInfo instance(){
        if(single==null){
            synchronized (ReceiverInfo.class){
                single = DaggerComponentDialogs.create().info();

            }
        }
        return single;
    }
}
