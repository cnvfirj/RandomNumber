package com.kittendevelop.randomnumber.ui.number.db;

import java.util.PropertyResourceBundle;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class NumbWorkDB {

    public void delete(BaseWorkDB workDB,BaseEntity entity,ResultWorkDB result){
//          workDB.delete(entity);
    }

    public void insert(BaseWorkDB workDB, BaseEntity entity,ResultWorkDB result){
//           workDB.insert(entity);
    }

    public void clear(BaseWorkDB workDB,ResultWorkDB result){
//        workDB.clear();
    }

    Observable<Boolean>workDB(int operation, BaseWorkDB workDB,BaseEntity entity){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> emitter) throws Exception {
                  boolean b = false;
                  emitter.onNext(selector(operation,workDB,entity));
                  emitter.onComplete();

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private boolean selector(int operation, BaseWorkDB workDB,BaseEntity entity){
        if(operation==0){
            return workDB.insert(entity);
        }else if(operation==1){
            return workDB.delete(entity);
        }else if(operation==2){
            return workDB.clear();
        }
        return false;
    }

}
