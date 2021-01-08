package com.kittendevelop.randomnumber.ui.number.dialog;

import androidx.fragment.app.DialogFragment;

import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;

import javax.inject.Inject;

public class ReceiverItem extends ReceiverDialogs{

    private static ReceiverItem single;
    @Inject
    public ReceiverItem(DialogItem mDialog) {
        super(mDialog);
    }

    public static ReceiverItem instance(){
        if(single==null){
            synchronized (ReceiverItem.class){
                single = DaggerComponentDialogs.create().item();
//                single = DaggerComponentDialogs.create().waiting();
            }
        }
        return single;
    }
}
