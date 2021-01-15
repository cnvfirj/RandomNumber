package com.kittendevelop.randomnumber.ui.number.work;

import android.net.ConnectivityManager;
import android.util.Pair;

import com.kittendevelop.randomnumber.ui.number.db.BaseEntity;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.db.FillNewBaseEntityItem;
import com.kittendevelop.randomnumber.ui.number.work.rest.NetService;
import com.kittendevelop.randomnumber.ui.number.work.rest.PojoNumber;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class ThreadRequestResult {

    private Set<Long> mEx;
    private long mFrom;
    private long mTo;
    private Observable<EntityGeneratedItem>mObservable;
    private Disposable mDisposable;
    private NetService mNetService;

    /*получаем элемент для проверки интернет соединения*/
    public ThreadRequestResult(NetService netService) {
        this.mNetService = netService;
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
       if(mDisposable!=null)mDisposable.dispose();
    }

    public Observable<EntityGeneratedItem>generatedItemObservable(){
        return Observable.create(new ObservableOnSubscribe<EntityGeneratedItem>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<EntityGeneratedItem> emitter) throws Exception {
                EntityGeneratedItem entity = new EntityGeneratedItem().confines(mFrom,mTo);
                FillNewBaseEntityItem.fill(entity);
                if(mNetService.check()){
                    generateNumberInNet(entity,emitter);

                }else {
                    generateNumberDev(entity,emitter);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation());
    }

    private void generateNumberInNet(EntityGeneratedItem entity,ObservableEmitter<EntityGeneratedItem> emitter){
        mNetService
                .call(8)
                .enqueue(new Callback<PojoNumber>() {
            @Override
            public void onResponse(Call<PojoNumber> call, Response<PojoNumber> response) {
                if(response.body()!=null&&response.body().getData()!=null) {
                    String code = response.body().getData().get(0);
                    BigInteger result = new BigInteger(code, 16);
                    long number = Math.abs(result.longValue());
                    addParams(entity, EntityGeneratedItem.SOURCE_NET, SearchRandomNumberNetwork.generate(mEx, mFrom, mTo, number));
                    emitter.onNext(entity);
                    emitter.onComplete();
                }else generateNumberDev(entity,emitter);
            }

            @Override
            public void onFailure(Call<PojoNumber> call, Throwable t) {
               generateNumberDev(entity,emitter);
            }
        });
    }

    private void generateNumberDev(EntityGeneratedItem entity,ObservableEmitter<EntityGeneratedItem> emitter){
        addParams(entity,EntityGeneratedItem.SOURCE_APP,SearchRandomNumberNonNet.generate(mEx,mFrom,mTo));
        emitter.onNext(entity);
        emitter.onComplete();
    }

    private void addParams(EntityGeneratedItem item, int source, long value){
        item.source(source);
        item.value(value);
    }


}
