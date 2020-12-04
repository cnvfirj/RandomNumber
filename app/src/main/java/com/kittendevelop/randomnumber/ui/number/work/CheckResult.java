package com.kittendevelop.randomnumber.ui.number.work;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class CheckResult {

    /*поиск совпадений в массиве*/
    public synchronized static long correctExcludeCik(long[]exceptions,long value,long min, long max,int step){
        if(value<exceptions[step])return value;
        if(value>max)return correctExcludeCik(exceptions,min,min,max,0);
        for (int i=step;i<exceptions.length;i++){
            if(value==exceptions[i]){
                return correctExcludeCik(exceptions,value+1,min,max,i);
            }
        }
        return value;
    }
    /*поиск совпадений бинарным способом*/
    public synchronized static long correctExcludeBin(long[]exceptions,long value,long min, long max){
        if(value>max)return correctExcludeBin(exceptions,min,min,max);
        if(checkСoincidence(exceptions,value,0,exceptions.length-1))
            return correctExcludeBin(exceptions,value+1,min,max);
        return value;
    }

    private static boolean checkСoincidence(long[]options,long value,int start, int end){
        int check = (end-start)/2+start;
        if(options[check]==value)return true;
        if(start==end)return false;
        else {
            long v = options[check];
            if(v<value){
                if(check+1>end)return false;
                else return checkСoincidence(options,value,check+1,end);
            }
            else if(v>value){
                if(check-1<start)return false;
                else return checkСoincidence(options,value,start,check-1);
            }
        }
        return false;
    }
}
