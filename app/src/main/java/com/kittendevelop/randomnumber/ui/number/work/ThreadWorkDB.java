package com.kittendevelop.randomnumber.ui.number.work;

import android.content.Context;
import android.net.ConnectivityManager;

import com.kittendevelop.randomnumber.ui.number.adapters.AdapterDataSource;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntity;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.CommonValues;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityExItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.db.FillNewBaseEntityItem;

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
    private AdapterDataSource mDataStory;
    private AdapterDataSource mDataEx;

    public ThreadWorkDB(DataBaseGeneratedItems dataBaseItems) {
        this.mDataBaseItems = dataBaseItems;
    }

    public DataBaseGeneratedItems data(){
        return mDataBaseItems;
    }

    public AdapterDataSource getAdapterDataSource(int table){
        if(table==0){
            if(mDataStory==null)mDataStory = new AdapterDataSource(mDataBaseItems,table);
            return mDataStory;
        }else {
            if(mDataEx==null)mDataEx = new AdapterDataSource(mDataBaseItems,table);
            return mDataEx;
        }
    }

    /*запрос в бд на исключенные числа*/
    public Observable<Set<Long>>listExValues(){
        return Observable.create(new ObservableOnSubscribe<Set<Long>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Set<Long>> emitter) throws Exception {
                Set<Long> list = new TreeSet<>();
                for (BaseEntityItems ex:mDataBaseItems.workWithEx().all()){
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
                /*роверка на ошибку*/
                if(entity.getNumber()!=Long.MIN_VALUE){
                    mDataBaseItems.workWithItems().insert(entity);
                    EntityGeneratedItem e = mDataBaseItems.workWithItems().id(entity.getId());
                    if(e!=null)emitter.onNext(e);
                }else emitter.onNext(entity);

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
