package com.kittendevelop.randomnumber.ui.number;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaActionSound;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.mainDI.CallbackMainAppModule;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;
import com.kittendevelop.randomnumber.ui.number.dialog.DialogResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverWaiting;
import com.kittendevelop.randomnumber.ui.number.work.ThreadRequest;

import java.util.TreeSet;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class PresenterNumb{

    private final SelectorInputBound mSelectorInputBound;
    private final ModelNumb mModelNumb;
    private final CallbackMainAppModule mAppCallback;
    private FragmentFeedback mFeedback;

//    @Inject
    public PresenterNumb(SelectorInputBound inputBound,ModelNumb modelNumb,CallbackMainAppModule callback) {
        mSelectorInputBound = inputBound;
        mModelNumb = modelNumb;
        mAppCallback = callback;
        initSelector();
    }

    public PresenterNumb bindView(FragmentFeedback view){
        mFeedback = view;
        return this;
    }

    public SelectorInputBound getSelector(){
        return mSelectorInputBound;
    }

    public void end(){
        saveParams();
    }

    public void pause(){
        mModelNumb.request().dispose();
    }

    private void initSelector(){
        SharedPreferences p = mAppCallback.preferences("OLD_VALUES_INPUT");
        long from = p.getLong("M_FROM_",0);
        long to = p.getLong("M_TO_",0);
        mSelectorInputBound
                .setFrom(from)
                .setTo(to)
                .setColorYes(mAppCallback.resources().getColor(R.color.willingness_yes,null))
                .setColorNo(mAppCallback.resources().getColor(R.color.willingness_no,null))
                .applyParams();
    }

    private void saveParams(){
        Editor editor = mAppCallback.editor("OLD_VALUES_INPUT");
        editor.putLong("M_FROM_",mSelectorInputBound.getFrom());
        editor.putLong("M_TO_",mSelectorInputBound.getTo());
        editor.apply();
    }

    public void click(View v){
        TreeSet<Long> set = new TreeSet<>();
        mFeedback.showDialog(ReceiverWaiting.instance().dialog(),"WAITING");
        mModelNumb.request().setParams(set,mSelectorInputBound.getFrom(),mSelectorInputBound.getTo()).internalObservable().internalDisposable(this::result);
    }

    /*тут надо или занести результат в базу данных,
    * а потом в диалоге из нее дергать последний результат.
    * Либо передавать результат в диалог, а потом оттуда
    * вносить его в базу данных.
    * А есть вариант, в процессе генерации результата, вносить его в базу данных*/
    public void result(Long result) throws Exception{
//        mModelNumb.request().dispose();
        ReceiverWaiting.instance().stop();
        mFeedback.showDialog(ReceiverResult.instance().result(result).dialog(),"RESULT");
    }
}
