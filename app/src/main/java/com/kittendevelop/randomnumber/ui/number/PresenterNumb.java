package com.kittendevelop.randomnumber.ui.number;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.ui.number.dialog.DialogResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverWaiting;
import com.kittendevelop.randomnumber.ui.number.work.ThreadRequest;

import java.util.TreeSet;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class PresenterNumb{

    private SelectorInputBound mSelectorInputBound;
    private FragmentFeedback mFeedback;
    private ModelNumb mModelNumb;

    @Inject
    public PresenterNumb(SelectorInputBound inputBound,ModelNumb modelNumb) {
        mSelectorInputBound = inputBound;
        mModelNumb = modelNumb;
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

    public void click(View v){
        TreeSet<Long> set = new TreeSet<>();
        mFeedback.showDialog(ReceiverWaiting.instance().dialog(),"WAITING");
        mModelNumb.request().setParams(set,mSelectorInputBound.getFrom(),mSelectorInputBound.getTo()).internalObservable().internalDisposable(this::result);
    }

    public void longClick(View v){

    }

    public void result(Long result) throws Exception{
        mModelNumb.request().stopInternal();
        ReceiverWaiting.instance().stop();
        mFeedback.showDialog(new DialogResult(),"RESULT");
    }
}
