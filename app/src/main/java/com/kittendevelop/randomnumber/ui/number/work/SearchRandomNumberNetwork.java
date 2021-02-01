package com.kittendevelop.randomnumber.ui.number.work;

import android.media.MediaActionSound;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class SearchRandomNumberNetwork {


    public static long generate(Set<Long> ex, long from, long to, long source){
        if(from==to)return Long.MAX_VALUE;
        if(Math.abs(to-from)<10000)return searchInNet2(ex, from, to,source);
        else return searchInNet(ex, from, to,source);
    }

    private static long searchInNet2(Set<Long> ex, long from, long to, long source){
        ArrayList<Long> list = new ArrayList<>();
        while (from<=to){
            if(!ex.contains(from)){
                list.add(from);
            }
            from++;
        }
        if(list.size()==0) return Long.MIN_VALUE;
        return list.get(generateIndex(list.size(),source));
    }

    private static int generateIndex(int size, long value){
        long index = value%size;
        return (int) Math.abs(value%size);
    }

    private static long searchInNet(Set<Long> ex, long from, long to,long source){

        return correct(ex,generateValue(from,to,source),from,to,0);
    }

    private static long generateValue(long from, long to, long source){
        long delta = (to-from)+1;
        long step = source%delta;
        return from+step;

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


}
