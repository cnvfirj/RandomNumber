package com.kittendevelop.randomnumber.ui.number.work;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class SearchRandomNumberNonNet {




    public static long generate(Set<Long> ex,long from,long to){
        if(Math.abs(to-from)<10000)return searchInDevice2(ex, from, to);
        else return searchInDevice(ex, from, to);
    }

    public static long searchInDevice(Set<Long> ex,long from,long to){
        long delta = (to-from)+1;
        long val =(long)(Math.random()*delta)+from;
        return correct(ex,val,from,to,0);
    }

    private static long correct(Set<Long> ex,long value,long from, long to, int index){
        if(index==2)return Long.MIN_VALUE;
        if(value>to){
            value = from;
            index++;
        }
        if(ex.contains(value)){
            return correct(ex,value+1,from,to,index);
        }else return value;
    }

    /*поиск с cозданием списка элементов. ищем случайный из них*/
    /*если диапазон чисел большой, то это громоздко*/
    public static long searchInDevice2(Set<Long> ex,long from,long to){
        ArrayList<Long> list = new ArrayList<>();
        while (from<=to){
            if(!ex.contains(from)){
                list.add(from);
            }
            from++;
        }
        if(list.size()==0) return Long.MIN_VALUE;
        double d = Math.random();
        int i = (int) (d*list.size());
        long value = list.get(i);
        list.clear();
        return value;
    }

    /*поиск числа из диапазона, с проверкой в списе исключений*/
    /*если список длинный то это громоздко*/
    public static long searchInDevice3(Set<Long> ex, long from, long to){

        long delta = (to-from)+1;
        long val = (long)(Math.random()*delta)+from;
        if(ex!=null&&ex.contains(val))return searchInDevice3(ex,from,to);
        return val;
    }
}
