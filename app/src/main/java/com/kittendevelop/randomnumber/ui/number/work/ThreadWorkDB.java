package com.kittendevelop.randomnumber.ui.number.work;

import android.content.Context;
import android.net.ConnectivityManager;

import com.kittendevelop.randomnumber.ui.number.db.BaseEntity;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityExItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.db.FillNewBaseEntityItem;
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


    /*запрос в бд на исключенные числа*/
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

    /*добавляем число в базу данный истории
    * Плюс возвращаем добавленный в бд элемент в качестве проверки*/
    public Observable<EntityGeneratedItem>insertWorkItemNumb(EntityGeneratedItem entity){
        return Observable.create(new ObservableOnSubscribe<EntityGeneratedItem>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<EntityGeneratedItem> emitter) throws Exception {
                mDataBaseItems.workWithItems().insert(entity);
                EntityGeneratedItem e = mDataBaseItems.workWithItems().id(entity.getId());
                if(e!=null)emitter.onNext(e);
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<EntityGeneratedEx>insertWorkItemEx(long value,int source){
        return Observable.create(new ObservableOnSubscribe<EntityGeneratedEx>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<EntityGeneratedEx> emitter) throws Exception {
                EntityGeneratedEx entity = new EntityGeneratedEx();
                entity.value(value).source(source);
                FillNewBaseEntityItem.fill(entity);
                mDataBaseItems.workWithEx().insert(entity);
                EntityGeneratedEx e = mDataBaseItems.workWithEx().id(entity.getId());
                if(e!=null)emitter.onNext(e);
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }



}
