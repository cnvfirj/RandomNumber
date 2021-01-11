package com.kittendevelop.randomnumber.ui.number.dialog;

import android.os.Bundle;

import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
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
            }
        }
        return single;
    }

    public ReceiverResult result(EntityGeneratedItem item){
        bundle().putSerializable("RESULT_ITEM",item);
        dialog().setArguments(bundle());
        return this;
    }

    private Bundle bundle(){
        if(mBundle==null)mBundle=new Bundle();
        return mBundle;
    }
}
