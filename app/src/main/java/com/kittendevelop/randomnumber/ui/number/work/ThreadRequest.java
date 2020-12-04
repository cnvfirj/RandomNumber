package com.kittendevelop.randomnumber.ui.number.work;

import java.util.TreeSet;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ThreadRequest {

    private TreeSet mEx;
    private long mFrom;
    private long mTo;
    private Observable<Long>mObservable;
    private Disposable mDisposable;

    @Inject
    public ThreadRequest() {
    }

    public ThreadRequest setParams(TreeSet<Long> ex, long from, long to){
        mEx = ex;
        mFrom = from;
        mTo = to;
        return this;
    }
    public ThreadRequest internalObservable(){
        if(mObservable==null)mObservable = emitter();
        return this;
    }
    public ThreadRequest internalDisposable(Consumer<Long> consumer){
        mDisposable = mObservable.subscribe(consumer);
        return this;
    }

    public void stopInternal(){
       mDisposable.dispose();
    }

    public Disposable subscribe(Consumer<Long> consumer){
        return mObservable.subscribe(consumer);
    }

    public Observable<Long>emitter(){

        return Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Long> emitter) throws Exception {
               emitter.onNext(SearchRandomNumberNonNet.searchInDevice3(mEx,mFrom,mTo));
               emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }




}
