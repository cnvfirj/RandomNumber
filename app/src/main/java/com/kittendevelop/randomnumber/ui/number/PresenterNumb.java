package com.kittendevelop.randomnumber.ui.number;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.help.ApplyFeedback;

import javax.inject.Inject;

public class PresenterNumb {

    private SelectorInputBound mSelectorInputBound;
    private FragmentFeedback mFeedback;

    @Inject
    public PresenterNumb(SelectorInputBound inputBound) {
        mSelectorInputBound = inputBound;
    }

    public PresenterNumb bindView(FragmentFeedback view){
        mFeedback = view;
        initSelector();
        return this;
    }

    public SelectorInputBound getSelector(){
        return mSelectorInputBound;
    }

    public void end(){
        saveParams();
        mFeedback = null;
    }

    private void initSelector(){
        SharedPreferences p = mFeedback.context().getSharedPreferences("OLD_VALUES_INPUT", Context.MODE_PRIVATE);
        long from = p.getLong("M_FROM_",0);
        long to = p.getLong("M_TO_",0);
        mSelectorInputBound
                .setFrom(from)
                .setTo(to)
                .setColorYes(mFeedback.context().getResources().getColor(R.color.willingness_yes,null))
                .setColorNo(mFeedback.context().getResources().getColor(R.color.willingness_no,null))
                .applyParams();
    }

    private void saveParams(){
        Editor editor = mFeedback.context().getSharedPreferences("OLD_VALUES_INPUT",Context.MODE_PRIVATE).edit();
        editor.putLong("M_FROM_",mSelectorInputBound.getFrom());
        editor.putLong("M_TO_",mSelectorInputBound.getTo());
        editor.apply();
    }
}
