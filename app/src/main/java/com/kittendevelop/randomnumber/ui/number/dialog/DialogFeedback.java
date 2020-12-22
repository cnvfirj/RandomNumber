package com.kittendevelop.randomnumber.ui.number.dialog;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.kittendevelop.randomnumber.help.ApplyFeedback;

public interface DialogFeedback extends ApplyFeedback {

    void exitDialog();
    DialogFragment fragment();
}
