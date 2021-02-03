package com.kittendevelop.randomnumber.ui.number.dialog;

import androidx.fragment.app.DialogFragment;

import com.kittendevelop.randomnumber.help.ApplyFeedback;

public interface DialogFeedback extends ApplyFeedback {

    void exitDialog();
    DialogFragment fragment();
}
