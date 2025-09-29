package com.example.catapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface TheCatApiService {

    @Headers("x-api-key: live_l4KulbTFzBrw0FSLosY11mId1e3Wh7Y6tqQsD7J720VPxCJxwMk1lJmwq7oY8I2c") // opcional, mas recomendado
    @GET("v1/images/search")
    Call<List<Cat>> getRandomCatImage();
}

