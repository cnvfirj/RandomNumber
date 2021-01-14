package com.kittendevelop.randomnumber.ui.number;

import android.annotation.SuppressLint;

import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.CommonValues;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.work.ThreadRequestResult;
import com.kittendevelop.randomnumber.ui.number.work.ThreadWorkDB;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ModelNumb {

    private ThreadRequestResult mThreadRequest;
    private ThreadWorkDB mThreadWorkDB;


    public ModelNumb(ThreadRequestResult threadRequest,ThreadWorkDB threadWorkDB) {
        mThreadRequest = threadRequest;
        mThreadWorkDB = threadWorkDB;
    }

    public ThreadRequestResult requestResultNumber(){
        return mThreadRequest;
    }


    public void requestListStory(DisposableSingleObserver<List<CommonValues>> consumer){
        mThreadWorkDB.storyItems()
                .subscribe(consumer);
    }

    public void requestListEx(DisposableSingleObserver<List<CommonValues>> consumer){
        mThreadWorkDB.exItems()
                .subscribe(consumer);
    }


    @SuppressLint("CheckResult")
    public void requestItemStory(long id, DisposableSingleObserver<BaseEntityItems>  consumer){
        mThreadWorkDB.data().workWithItems().idRx(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    @SuppressLint("CheckResult")
    public void requestItemEx(long id, DisposableSingleObserver<BaseEntityItems> consumer){
        mThreadWorkDB.data().workWithEx().idRx(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }



    @SuppressLint("CheckResult")
    public void requestGeneratedNumber(Consumer<EntityGeneratedItem>consumer, long from, long to){
        mThreadRequest.setParams(from,to);
       mThreadWorkDB
               .listExValues()//запрашиваем в бд исключения
               .flatMap(requestNumber())//генерируем число
               .flatMap(addToDB())//добавляем его в базу данных
               .subscribe(consumer);
    }

    @SuppressLint("CheckResult")
    public void requestGeneratedEx(Consumer<EntityGeneratedEx>consumer, long value, int source){
        mThreadWorkDB.insertWorkItemEx(value,source).subscribe(consumer);
    }

    @SuppressLint("CheckResult")
    public void deleteItemStory(BaseEntityItems item, Consumer<List<CommonValues>> consumer){
         mThreadWorkDB.delItemStory((EntityGeneratedItem)item).subscribe(consumer);
    }

    @SuppressLint("CheckResult")
    public void deleteItemEx(BaseEntityItems item, Consumer<List<CommonValues>> consumer){
        mThreadWorkDB.delItemEx((EntityGeneratedEx)item).subscribe(consumer);
    }

    public void clearStory(DisposableSingleObserver<Boolean> consumer){
        mThreadWorkDB.clearTable(0).subscribe(consumer);
    }

    public void clearEx(DisposableSingleObserver<Boolean> consumer){
        mThreadWorkDB.clearTable(1).subscribe(consumer);
    }

    private Function<Set<Long>,Observable<EntityGeneratedItem>>requestNumber(){
        return new Function<Set<Long>, Observable<EntityGeneratedItem>>() {
            @Override
            public Observable<EntityGeneratedItem> apply(@NonNull Set<Long> longs) throws Exception {
                mThreadRequest.setEx(longs);
                return mThreadRequest.generatedItemObservable();
            }
        };
    }

    private Function<EntityGeneratedItem,Observable<EntityGeneratedItem>>addToDB(){
        return new Function<EntityGeneratedItem, Observable<EntityGeneratedItem>>() {
            @Override
            public Observable<EntityGeneratedItem> apply(@NonNull EntityGeneratedItem item) throws Exception {
                return mThreadWorkDB.insertWorkItemNumb(item);
            }
        };
    }

}
