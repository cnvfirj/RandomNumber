package com.kittendevelop.randomnumber.ui.number;

import android.annotation.SuppressLint;

import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.work.ThreadRequestResult;
import com.kittendevelop.randomnumber.ui.number.work.ThreadWorkDB;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
