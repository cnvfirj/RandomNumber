package com.kittendevelop.randomnumber.ui.number.work;

import android.content.Context;
import android.net.ConnectivityManager;

import com.kittendevelop.randomnumber.ui.number.db.BaseEntity;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityExItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.db.SelectEx;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class ThreadWorkDB {

    private DataBaseGeneratedItems mDataBaseItems;

    public ThreadWorkDB(DataBaseGeneratedItems dataBaseItems) {
        this.mDataBaseItems = dataBaseItems;
    }

    public Observable<Set<Long>>listExValues(){
        return Observable.create(new ObservableOnSubscribe<Set<Long>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Set<Long>> emitter) throws Exception {
                Set<Long> list = new TreeSet<>();
                for (SelectEx ex:mDataBaseItems.workWithEx().allValues()){
                    list.add(ex.mNumber);
                }
                emitter.onNext(list);
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<EntityGeneratedItem>insertWorkItemNumb(EntityGeneratedItem entity){
        return Observable.create(new ObservableOnSubscribe<EntityGeneratedItem>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<EntityGeneratedItem> emitter) throws Exception {
//                MASSAGE("insert "+entity.getNumber());
                mDataBaseItems.workWithItems().insert(entity);
//                MASSAGE("get "+entity.getValue());

                emitter.onNext(mDataBaseItems.workWithItems().id(entity.getId()));
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError(throwable -> {MASSAGE("error insert "+throwable.getMessage());});
    }



}
