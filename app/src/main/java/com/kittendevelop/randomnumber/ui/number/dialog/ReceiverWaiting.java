package com.kittendevelop.randomnumber.ui.number.dialog;

import android.app.Dialog;

public class ReceiverWaiting {

    private static ReceiverWaiting single;

    private DialogWaiting mWaiting;

    public ReceiverWaiting() {
        mWaiting = new DialogWaiting();
    }

    public static ReceiverWaiting instance(){
        if(single==null){
            synchronized (ReceiverWaiting.class){
                single = new ReceiverWaiting();
            }
        }
        return single;
    }

    public void stop(){
        if(isAdded())mWaiting.dismiss();
    }

    public boolean isAdded(){
        return mWaiting.isAdded();
    }

    public DialogWaiting dialog(){
        return mWaiting;
    }

}
