package com.kittendevelop.randomnumber.ui.number.work;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class SearchRandomNumberNonNet {

    /*поиск с cозданием списка элементов. ищем случайный из них*/
    /*если диапазон чисел большой, то это громоздко*/
    public static long searchInDevice2(TreeSet<Long>ex,long from,long to){
        ArrayList<Long> list = new ArrayList<>();
        while (from<=to){
            if(!ex.contains(from)){
                list.add(from);
            }
            from++;
        }
        double d = Math.random();
        int i = (int) (d*list.size());
        return list.get(i);
    }

    /*поиск числа из диапазона, с проверкой в списе исключений*/
    /*если список длинный то это громоздко*/
    public static long searchInDevice3(TreeSet<Long>ex,long from,long to){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long delta = (to-from)+1;
        long val = (long)(Math.random()*delta)+from;
        if(ex.contains(val))return searchInDevice3(ex,from,to);
        return val;
    }



}
