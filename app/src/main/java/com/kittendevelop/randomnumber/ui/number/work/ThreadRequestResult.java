package com.kittendevelop.randomnumber.ui.number.work;

import android.net.ConnectivityManager;
import android.util.Pair;

import com.kittendevelop.randomnumber.ui.number.db.BaseEntity;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.db.FillNewBaseEntityItem;

import java.util.List;
import java.util.Set;
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

    private Set<Long> mEx;
    private long mFrom;
    private long mTo;
    private Observable<EntityGeneratedItem>mObservable;
    private Disposable mDisposable;
    private ConnectivityManager mConnect;

    /*получаем элемент для проверки интернет соединения*/
    public ThreadRequestResult(ConnectivityManager connect) {
        this.mConnect = connect;
    }

    public ThreadRequestResult setEx(Set<Long> ex){
        mEx = ex;
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

    public void internalDisposable(Consumer<EntityGeneratedItem> consumer){
        mDisposable = mObservable.subscribe(consumer);
    }




    public void dispose(){
       mDisposable.dispose();
    }

    public Observable<EntityGeneratedItem>generatedItemObservable(){
        return Observable.create(new ObservableOnSubscribe<EntityGeneratedItem>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<EntityGeneratedItem> emitter) throws Exception {
                EntityGeneratedItem entity = new EntityGeneratedItem().confines(mFrom,mTo);
                FillNewBaseEntityItem.fill(entity);
                if(SearchRandomNumberNetwork.check()){
                    addParams(entity,EntityGeneratedItem.SOURCE_NET,SearchRandomNumberNetwork.searchInNet(mEx,mFrom,mTo));

                }else {
                    addParams(entity,EntityGeneratedItem.SOURCE_APP,SearchRandomNumberNonNet.generate(mEx,mFrom,mTo));
                }
                emitter.onNext(entity);
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation());
    }

    private void addParams(EntityGeneratedItem item, int source, long value){
        item.source(source);
        item.value(value);
    }


}
