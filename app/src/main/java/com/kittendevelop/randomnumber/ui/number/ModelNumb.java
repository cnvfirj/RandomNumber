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
    public long correctExclude(long[]exceptions,long value,long min, long max,int step){
        MASSAGE("recurs "+value);
        if(value<exceptions[step])return value;
        if(value>max)correctExclude(exceptions,min,min,max,0);
        for (int i=step;i<exceptions.length;i++){
            if(value==exceptions[i]){
                correctExclude(exceptions,value+1,min,max,i);
                break;
            }
        }
        return value;
    }
}
