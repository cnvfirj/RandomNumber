package com.kittendevelop.randomnumber.ui.number;

import androidx.fragment.app.DialogFragment;

import com.kittendevelop.randomnumber.help.ApplyFeedback;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;

public interface FragmentFeedback extends ApplyFeedback {
    void showDialog(DialogFragment fragment,String tag);
//    DataBaseGeneratedItems dataBase();
}
