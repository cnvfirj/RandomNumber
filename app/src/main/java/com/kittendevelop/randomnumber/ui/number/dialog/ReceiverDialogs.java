package com.kittendevelop.randomnumber.ui.number.dialog;

import androidx.fragment.app.DialogFragment;

public abstract class ReceiverDialogs {

    private DialogFragment mDialog;

    public ReceiverDialogs(DialogFragment mDialog) {
        this.mDialog = mDialog;
    }

    public void stop(){
        if(isShowing())mDialog.dismiss();
    }

    public boolean isShowing(){
        return mDialog.getDialog().isShowing();
    }

    public DialogFragment dialog(){
        return mDialog;
    }
}
