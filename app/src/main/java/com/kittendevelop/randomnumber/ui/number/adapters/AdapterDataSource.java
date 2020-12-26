package com.kittendevelop.randomnumber.ui.number.adapters;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.CommonValues;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AdapterDataSource extends PositionalDataSource<CommonValues> {

    private DataBaseGeneratedItems mdb;
    private int mTable;

    public AdapterDataSource(DataBaseGeneratedItems mdb, int table) {
        this.mdb = mdb;
        this.mTable = table;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<CommonValues> callback) {
          query().subscribe(new Consumer<List<CommonValues>>() {
              @Override
              public void accept(List<CommonValues> commonValues) throws Exception {
                  callback.onResult(commonValues,0);
              }
          });
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<CommonValues> callback) {
         query().subscribe(new Consumer<List<CommonValues>>() {
             @Override
             public void accept(List<CommonValues> commonValues) throws Exception {
                 callback.onResult(commonValues);
             }
         });
    }

    private Observable<List<CommonValues>>query(){
        return Observable.create(new ObservableOnSubscribe<List<CommonValues>>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<List<CommonValues>> emitter) throws Exception {
                 emitter.onNext(list());
                 emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private List<CommonValues>list(){
        if(mTable==0)return sort(mdb.workWithItems().commonValues());
        else return sort(mdb.workWithEx().commonValues());
    }

    private List<CommonValues>sort(List<CommonValues>list){
        if(mTable==0) {
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
