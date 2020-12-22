package com.kittendevelop.randomnumber.ui.number;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.mainDI.CallbackMainAppModule;
import com.kittendevelop.randomnumber.mainDI.MainApplication;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverWaiting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class PresenterNumb{


    private final SelectorInputBound mSelectorInputBound;
    private final ModelNumb mModelNumb;
    private final CallbackMainAppModule mAppCallback;
    private FragmentFeedback mFeedback;

    public PresenterNumb(SelectorInputBound inputBound,ModelNumb modelNumb,CallbackMainAppModule callback) {
        mSelectorInputBound = inputBound;
        mModelNumb = modelNumb;
        mAppCallback = callback;
        initSelector();
    }

    public void bindView(FragmentFeedback view){
        mFeedback = view;

    }

    public SelectorInputBound getSelector(){
        return mSelectorInputBound;
    }

    public void end(){
        saveParams();
    }

    public void pause(){
        mModelNumb.requestResultNumber().dispose();
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
        mFeedback.showDialog(ReceiverWaiting.instance().dialog(),"WAITING");
        mModelNumb.requestGeneratedNumber(
                this::resultRequestEntity,mSelectorInputBound.getFrom(),mSelectorInputBound.getTo()
        );
    }

    /*после выполнения всей работы закрываем ожидание
    * и открываем диалог с результатом.
    * В бд истории результат добавлен*/
    public void resultRequestEntity(EntityGeneratedItem item) throws Exception{
        ReceiverWaiting.instance().stop();
        mFeedback.showDialog(
                ReceiverResult.instance().result(item.getNumber()).dialog(),ReceiverResult.TAG
        );
    }

    public void addValueToEX(long value){
        mModelNumb.requestGeneratedEx(
                this::resultRequestEx,value,EntityGeneratedEx.SOURCE_AUTO
        );
    }

    public void resultRequestEx(EntityGeneratedEx item) throws Exception{
        MASSAGE("add to ex "+item.getValue());
    }



}
