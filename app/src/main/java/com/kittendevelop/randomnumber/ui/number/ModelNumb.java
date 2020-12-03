package com.kittendevelop.randomnumber.ui.number;

import javax.inject.Inject;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class ModelNumb {

//    private StringBuilder mValueFrom;
//    private StringBuilder mValueTo;
//
//    public ModelMain() {
//        mValueFrom = new StringBuilder();
//        mValueTo = new StringBuilder();
//    }


    @Inject
    public ModelNumb() {
    }

    public long searchInDevice(long from, long to){
        long delta = (to-from)+1;
        double d = Math.random()*delta;
        return (long)d+from;
    }

    /*массив отсортирован по возрастанию*/
    public long correctExclude(long[]exceptions,long value,long min, long max){
        if(value>max)return correctExclude(exceptions,min,min,max);
        if(checkСoincidence(exceptions,value,0,exceptions.length-1))
            return correctExclude(exceptions,value+1,min,max);
        return value;
    }

    private boolean checkСoincidence(long[]options,long value,int start, int end){
        int check = (end-start)/2+start;
        if(options[check]==value)return true;
        if(start==end)return false;
        else {
            long v = options[check];
            if(v<value){
                return checkСoincidence(options,value,check+1,end);
            }
            else if(v>value){
                return checkСoincidence(options,value,start,check-1);
            }
        }
        return false;
    }
}
