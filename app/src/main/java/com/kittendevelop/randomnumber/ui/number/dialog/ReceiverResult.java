package com.kittendevelop.randomnumber.ui.number.dialog;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

//import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;

import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;

import javax.inject.Inject;

public class ReceiverResult extends ReceiverDialogs{

    public static final String TAG = "RESULT";

    private static ReceiverResult single;

    private Bundle mBundle;
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

    public ReceiverResult result(long result){
        bundle().putString("RESULT_NUMB",Long.toString(result));
        dialog().setArguments(bundle());
        return this;
    }

    private Bundle bundle(){
        if(mBundle==null)mBundle=new Bundle();
        return mBundle;
    }
}
