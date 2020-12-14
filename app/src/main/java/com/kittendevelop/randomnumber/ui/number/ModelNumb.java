package com.kittendevelop.randomnumber.ui.number;

import com.kittendevelop.randomnumber.ui.number.work.SearchRandomNumberNonNet;
import com.kittendevelop.randomnumber.ui.number.work.ThreadRequest;

import java.util.TreeSet;

import javax.inject.Inject;

public class ModelNumb {

    private ThreadRequest mThreadRequest;

//    @Inject
    public ModelNumb(ThreadRequest threadRequest) {
        mThreadRequest = threadRequest;
    }

    public long searchInDevice(TreeSet<Long> ex,long from, long to){
        return SearchRandomNumberNonNet.searchInDevice3(ex,from,to);
    }

    public ThreadRequest request(){
        return mThreadRequest;
    }

}
