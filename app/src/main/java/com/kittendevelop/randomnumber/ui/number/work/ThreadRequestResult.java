package com.kittendevelop.randomnumber.ui.number.work;

import android.util.Pair;

import com.kittendevelop.randomnumber.ui.number.db.BaseEntity;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.db.FillNewBaseEntityItem;

import java.util.List;
import java.util.TreeSet;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ThreadRequestResult {

    private List<Long> mEx;
    private long mFrom;
    private long mTo;
    private Observable<EntityGeneratedItem>mObservable;
    private Disposable mDisposable;

    public ThreadRequestResult() {
    }

    public ThreadRequestResult setParams(List<Long> ex, long from, long to){
        mEx = ex;
        mFrom = from;
        mTo = to;
        return this;
    }

    public ThreadRequestResult setParams(long from, long to){
        mFrom = from;
        mTo = to;
        return this;
    }


    public ThreadRequestResult internalObservable(){
        if(mObservable==null)mObservable = generatedItemObservable();
        return this;
    }
//    public void internalDisposable(Consumer<Long> consumer){
//        mDisposable = mObservable.subscribe(consumer);
//    }

    public void internalDisposable(Consumer<EntityGeneratedItem> consumer){
        mDisposable = mObservable.subscribe(consumer);
    }



    public void dispose(){
       mDisposable.dispose();
    }

//    public Disposable subscribe(Consumer<Long> consumer){
//        return mObservable.subscribe(consumer);
//    }

//    private Observable<Long>emitter(){
//
//        return Observable.create(new ObservableOnSubscribe<Long>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Long> emitter) throws Exception {
//                /*здесь запрос в bазу исключений, из которых сформируем mEx*/
//               emitter.onNext(SearchRandomNumberNonNet.searchInDevice3(mEx,mFrom,mTo));
//               emitter.onComplete();
//            }
//        }).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io());
//    }


    private Observable<EntityGeneratedItem>generatedItemObservable(){
        return Observable.create(new ObservableOnSubscribe<EntityGeneratedItem>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<EntityGeneratedItem> emitter) throws Exception {
                /*здесь запрос в bазу исключений, из которых сформируем mEx*/
                EntityGeneratedItem entity = new EntityGeneratedItem().confines(mFrom,mTo);
                FillNewBaseEntityItem.fill(entity);
                if(SearchRandomNumberNetwork.check()){
                    entity.source(EntityGeneratedItem.SOURCE_NET);
                    entity.value(SearchRandomNumberNetwork.searchInNet(mEx,mFrom,mTo));
                }else {
                    entity.source(EntityGeneratedItem.SOURCE_APP);
                    entity.value(SearchRandomNumberNonNet.searchInDevice3(mEx,mFrom,mTo));
                }
                emitter.onNext(entity);
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
