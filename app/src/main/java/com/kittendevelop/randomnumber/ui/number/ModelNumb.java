package com.kittendevelop.randomnumber.ui.number;

import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.work.ThreadRequestResult;
import com.kittendevelop.randomnumber.ui.number.work.ThreadWorkDB;

import java.util.List;
import java.util.TreeSet;

import io.reactivex.functions.Consumer;

public class ModelNumb {

    private ThreadRequestResult mThreadRequest;
    private ThreadWorkDB mThreadWorkDB;

//    @Inject
    public ModelNumb(ThreadRequestResult threadRequest,ThreadWorkDB threadWorkDB) {
        mThreadRequest = threadRequest;
        mThreadWorkDB = threadWorkDB;
    }

    public ThreadRequestResult requestResultNumber(){
        return mThreadRequest;
    }

    public void requestNumber(Consumer<EntityGeneratedItem>consumer, long from, long to){
        mThreadRequest.setParams(from, to).internalObservable().internalDisposable(consumer);
    }

    public void requestGeneratedNumber(Consumer<EntityGeneratedItem>consumer,long from,long to){

    }

//    public void addNumberInStory(Consumer<Boolean>consumer, String value, )

}
