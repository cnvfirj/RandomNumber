package com.kittendevelop.randomnumber.ui.number;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;

import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.mainDI.CallbackMainAppModule;
import com.kittendevelop.randomnumber.ui.number.adapters.AdapterList;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.CommonValues;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentAdapter;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverItem;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverWaiting;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class PresenterNumb{

    private final SelectorInputBound mSelectorInputBound;
    private final ModelNumb mModelNumb;
    private final CallbackMainAppModule mAppCallback;
    private FragmentFeedback mFeedback;

    private final AdapterList mAdapterStory;
    private final AdapterList mAdapterEx;


    public PresenterNumb(SelectorInputBound inputBound,ModelNumb modelNumb,CallbackMainAppModule callback) {
        mSelectorInputBound = inputBound;
        mModelNumb = modelNumb;
        mAppCallback = callback;
        mAdapterStory = DaggerComponentAdapter.create().adapterItems();
        mAdapterEx = DaggerComponentAdapter.create().adapterItems();
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

    public void start(){

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
    private void resultRequestEntity(EntityGeneratedItem item) throws Exception{
        ReceiverWaiting.instance().stop();
        mFeedback.showDialog(ReceiverResult.instance().result(item).dialog(),ReceiverResult.TAG);
        if(!item.getValue().equals("ERROR")){
              fillAdapterStory();
        }
    }

    /*добавляем исключение в бд*/
    public void addValueToEX(long value,int source){
        mModelNumb.requestGeneratedEx(
                this::resultRequestEx,value,source
        );
    }

    public void deleteItem(BaseEntityItems item, String tag){
        if(tag.equals(ReceiverItem.TAG_STORY)){
            mFeedback.showDialog(ReceiverWaiting.instance().dialog(),"WAITING");
           mModelNumb.deleteItemStory(item, this::acceptDelItemStory);
        } else if (tag.equals(ReceiverItem.TAG_EX)) {
            mFeedback.showDialog(ReceiverWaiting.instance().dialog(),"WAITING");
            mModelNumb.deleteItemEx(item, this::acceptDelItemEx);
        }
    }

    public void clearTable(String tag){
        mFeedback.showDialog(ReceiverWaiting.instance().dialog(),"WAITING");
        if(tag.equals(ReceiverItem.TAG_STORY)){
            mModelNumb.clearStory(new DisposableSingleObserver<Boolean>() {
                @Override
                public void onSuccess(@NonNull Boolean aBoolean) {
                   if(aBoolean) {
                       mAdapterStory.list(null);
                       ReceiverWaiting.instance().stop();
                   }
                   else fillAdapterStory();
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });
        } else if (tag.equals(ReceiverItem.TAG_EX)) {
            mModelNumb.clearEx(new DisposableSingleObserver<Boolean>() {
                @Override
                public void onSuccess(@NonNull Boolean aBoolean) {
                    if(aBoolean){
                        mAdapterEx.list(null);
                        ReceiverWaiting.instance().stop();
                    }
                    else fillAdapterEx();
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });

        }
    }

    public void resultRequestEx(EntityGeneratedEx item) throws Exception{
        fillAdapterEx();
    }

    public void fillLists(RecyclerView st, RecyclerView ex){
        fillListStory(st);
        fillListEx(ex);
    }

    private void fillAdapterStory(){
        mModelNumb.requestListStory(new DisposableSingleObserver<List<CommonValues>>() {
            @Override
            public void onSuccess(@NonNull List<CommonValues> list) {
                mAdapterStory.list(list);
                ReceiverWaiting.instance().stop();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    private void fillAdapterEx(){
        mModelNumb.requestListEx(new DisposableSingleObserver<List<CommonValues>>() {
            @Override
            public void onSuccess(@NonNull List<CommonValues> list) {
                mAdapterEx.list(list);
                ReceiverWaiting.instance().stop();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    private void fillListEx(RecyclerView recycler){
        fillAdapterEx();
        mAdapterEx.setListen((item, position) -> {
            mFeedback.showDialog(ReceiverWaiting.instance().dialog(),"WAITING");
            mModelNumb.requestItemEx(item.mId, new DisposableSingleObserver<BaseEntityItems>() {
                @Override
                public void onSuccess(@NonNull BaseEntityItems item) {
                    ReceiverWaiting.instance().stop();
                    mFeedback.showDialog(ReceiverItem.instance().item(item).dialog(),ReceiverItem.TAG_EX);
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });
        });
        recycler.setAdapter(mAdapterEx);
    }

    private void fillListStory(RecyclerView recycler){
        correctColumnsListStory(recycler);
        fillAdapterStory();
        mAdapterStory.setListen((item, position) -> {
            mFeedback.showDialog(ReceiverWaiting.instance().dialog(),"WAITING");
            mModelNumb.requestItemStory(item.mId, new DisposableSingleObserver<BaseEntityItems>() {
           @Override
           public void onSuccess(@NonNull BaseEntityItems item) {
               ReceiverWaiting.instance().stop();
               mFeedback.showDialog(ReceiverItem.instance().item(item).dialog(),ReceiverItem.TAG_STORY);
           }

           @Override
           public void onError(@NonNull Throwable e) {

           }
       });
        });
        recycler.setAdapter(mAdapterStory);
    }

    private void correctColumnsListStory(RecyclerView st){
        int orientation = mFeedback.context().getResources().getConfiguration().orientation;
        int width = mAppCallback.resources().getDisplayMetrics().widthPixels;
//        int height = mAppCallback.resources().getDisplayMetrics().heightPixels;
        int margin = (int) mAppCallback.resources().getDimension(R.dimen.item_list_margin);
        int item = (int)mAppCallback.resources().getDimension(R.dimen.item_width);
        int main = (int)(mAppCallback.resources().getDimension(R.dimen.main_margin))*2;

        if(orientation== Configuration.ORIENTATION_LANDSCAPE){

            width = (width/7)*3;
            int w = width-main;
            int count = (w/(item+margin*2));
            ((GridLayoutManager)st.getLayoutManager()).setSpanCount(count);
        } else {
            int w = width-main;
            int count = (w/(item+margin*2));
            ((GridLayoutManager)st.getLayoutManager()).setSpanCount(count);
        }
    }

    private void acceptDelItemStory(List<CommonValues> list) throws Exception {
             mModelNumb.requestListStory(new DisposableSingleObserver<List<CommonValues>>() {
                 @Override
                 public void onSuccess(@NonNull List<CommonValues> list) {
                     ReceiverWaiting.instance().stop();
                     mAdapterStory.list(list);
                 }

                 @Override
                 public void onError(@NonNull Throwable e) {
                     ReceiverWaiting.instance().stop();
                 }
             });

    }

    private void acceptDelItemEx(List<CommonValues> list) throws Exception {
        mModelNumb.requestListEx(new DisposableSingleObserver<List<CommonValues>>() {
            @Override
            public void onSuccess(@NonNull List<CommonValues> list) {
                ReceiverWaiting.instance().stop();
                mAdapterEx.list(list);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                ReceiverWaiting.instance().stop();
            }
        });
    }

    public interface ListenItems{
        void item(CommonValues item, int position);
    }

}
