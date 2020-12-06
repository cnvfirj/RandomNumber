package com.kittendevelop.randomnumber.ui.number.dialog;

import androidx.fragment.app.DialogFragment;

import com.kittendevelop.randomnumber.ui.number.df.DaggerComponentDialogs;

import javax.inject.Inject;

public class ReceiverResult extends ReceiverDialogs{

    private static ReceiverResult single;

    @Inject
    public ReceiverResult(DialogResult mDialog) {
        super(mDialog);
    }

    public static ReceiverResult instance(){
        if(single==null){
            synchronized (ReceiverWaiting.class){
                single = DaggerComponentDialogs.create().result();
//                single = new ReceiverResult(new DialogResult());
            }
        }
        return single;
    }
}
