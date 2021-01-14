package com.kittendevelop.randomnumber.ui.number.dialog;

import androidx.fragment.app.DialogFragment;

public abstract class ReceiverDialogs {

    protected final DialogFragment mDialog;

    public ReceiverDialogs(DialogFragment mDialog) {
        this.mDialog = mDialog;
    }

    public void stop(){
        if(mDialog!=null&&mDialog.getDialog()!=null){
            if(isAdded()) mDialog.dismiss();
        }
    }

    public void remove(){
        mDialog.dismiss();
    }

    public boolean isAdded(){
        return mDialog.isResumed()||mDialog.isVisible()||mDialog.isAdded();
    }

    public DialogFragment dialog(){
        return mDialog;
    }


}
