package com.kittendevelop.randomnumber.ui.number.work;


import com.kittendevelop.randomnumber.ui.number.adapters.AdapterDataSource;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.CommonValues;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.db.FillNewBaseEntityItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


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

    public Single<List<CommonValues>>delItemStory(EntityGeneratedItem item){
        return Single.create(new SingleOnSubscribe<List<CommonValues>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<CommonValues>> emitter) throws Exception {
                mDataBaseItems.workWithItems().delete(item);
                EntityGeneratedItem i = mDataBaseItems.workWithItems().id(item.getId());
                if(mDataBaseItems.workWithItems().id(item.getId())==null)
                emitter.onSuccess(sort(queryListCommonValues(0),0));
                else emitter.onError(new Throwable());
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Single<List<CommonValues>>delItemEx(EntityGeneratedEx item){
        return Single.create(new SingleOnSubscribe<List<CommonValues>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<CommonValues>> emitter) throws Exception {
                mDataBaseItems.workWithEx().delete(item);
                if(mDataBaseItems.workWithEx().id(item.getId())==null)
                    emitter.onSuccess(sort(queryListCommonValues(1),1));
                else emitter.onError(new Throwable());
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Single<List<CommonValues>>exItems(){
        return Single.create(new SingleOnSubscribe<List<CommonValues>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<CommonValues>> emitter) throws Exception {
                emitter.onSuccess(sort(queryListCommonValues(1),1));
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Single<List<CommonValues>>storyItems(){
        return Single.create(new SingleOnSubscribe<List<CommonValues>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<CommonValues>> emitter) throws Exception {
                emitter.onSuccess(sort(queryListCommonValues(0),0));
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }



    private List<CommonValues> queryListCommonValues(int table){
        if(table==0){
            return mDataBaseItems.workWithItems().commonValues();
        }else {
            return mDataBaseItems.workWithEx().commonValues();
        }
    }

    private List<CommonValues> sort(List<CommonValues> list,int compare){
        if(compare==0) {
            Collections.sort(list, new Comparator<CommonValues>() {
                @Override
                public int compare(CommonValues c1, CommonValues t1) {
                    Long d1 = c1.mId;
                    Long d2 = t1.mId;
                    return d2.compareTo(d1);
                }
            });

        }else {
            Collections.sort(list, new Comparator<CommonValues>() {
                @Override
                public int compare(CommonValues c1, CommonValues t1) {
                    Long d1 = c1.mNumber;
                    Long d2 = t1.mNumber;
                    return d1.compareTo(d2);
                }
            });
        }
        return list;
    }


}
