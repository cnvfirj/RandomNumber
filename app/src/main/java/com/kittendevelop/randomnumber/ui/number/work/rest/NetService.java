package com.kittendevelop.randomnumber.ui.number.work.rest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetService {

    private Retrofit mRetrofit;
    private JsonHolderApi mHolder;
    private final String URL = "https://qrng.anu.edu.au/API/";

    public NetService(Retrofit.Builder builder) {
        this.mRetrofit = builder.baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        mHolder = holderApi();
    }

    public boolean check(){
        return isOnline();
    }

    private boolean isOnline() {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);
            sock.connect(sockaddr, timeoutMs);
            sock.close();
            return true;
        } catch (IOException e) { return false; }
    }

    private JsonHolderApi holderApi(){
        return mRetrofit.create(JsonHolderApi.class);
    }

    public Call<PojoNumber>call(int size){
        return mHolder.getRandomNumber(1,"hex16",size);
    }
}
