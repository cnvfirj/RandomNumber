package com.kittendevelop.randomnumber.ui.number;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.mainDI.CallbackMainAppModule;
import com.kittendevelop.randomnumber.ui.number.adapters.EntityItemsAdapter;
import com.kittendevelop.randomnumber.ui.number.db.CommonValues;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentAdapter;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverWaiting;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PresenterNumb{


    private final SelectorInputBound mSelectorInputBound;
    private final ModelNumb mModelNumb;
    private final CallbackMainAppModule mAppCallback;
    private FragmentFeedback mFeedback;

    private EntityItemsAdapter mAdapterStory;
    private EntityItemsAdapter mAdapterEx;

    public PresenterNumb(SelectorInputBound inputBound,ModelNumb modelNumb,CallbackMainAppModule callback) {
        mSelectorInputBound = inputBound;
        mModelNumb = modelNumb;
        mAppCallback = callback;
        mAdapterStory = DaggerComponentAdapter.create().adapter();
        mAdapterEx = DaggerComponentAdapter.create().adapter();
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
        mFeedback.showDialog(ReceiverResult.instance().result(item).dialog(),ReceiverResult.TAG);
        if(!item.getValue().equals("ERROR"))
            mAdapterStory.submitList(pagedList(mModelNumb.dataSource(0),config(80)));
    }

    public void addValueToEX(long value){
        mModelNumb.requestGeneratedEx(
                this::resultRequestEx,value,EntityGeneratedEx.SOURCE_AUTO
        );
    }

    public void resultRequestEx(EntityGeneratedEx item) throws Exception{
        mAdapterEx.submitList(pagedList(mModelNumb.dataSource(1),config(20)));
    }

    public void fillLists(RecyclerView st, RecyclerView ex){

        ((GridLayoutManager)st.getLayoutManager()).setSpanCount(5);
        mAdapterStory.submitList(pagedList(mModelNumb.dataSource(0),config(680)));
        mAdapterEx.submitList(pagedList(mModelNumb.dataSource(1),config(20)));
        st.setAdapter(mAdapterStory);
        ex.setAdapter(mAdapterEx);
    }

    private PagedList.Config config(int pagedSize){
        return new PagedList.Config.Builder()
                .setPageSize(pagedSize)
                .build();
    }

    private PagedList<CommonValues> pagedList(PositionalDataSource<CommonValues> source, PagedList.Config config){
        return new PagedList.Builder<>(source,config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setNotifyExecutor(new MainThreadExecutor())
                .build();
    }

    static class MainThreadExecutor implements Executor {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mHandler.post(command);
        }
    }

}
