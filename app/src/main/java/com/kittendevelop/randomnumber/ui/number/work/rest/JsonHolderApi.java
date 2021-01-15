package com.kittendevelop.randomnumber.ui.number.work.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonHolderApi {
    @GET("jsonI.php")
    Call<PojoNumber> getRandomNumber(@Query("length")int length,@Query("type")String type,@Query("size") int size);
}
