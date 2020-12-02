package com.kittendevelop.randomnumber.ui.number;

import androidx.fragment.app.DialogFragment;

import com.kittendevelop.randomnumber.help.ApplyFeedback;

public interface FragmentFeedback extends ApplyFeedback {

    void startSearch(DialogFragment fragment);
    void stopSearch(DialogFragment fragment);
}
