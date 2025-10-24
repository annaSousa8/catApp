package com.example.catapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface TheCatApiService {

    @Headers("x-api-key: live_dbb7MPXL96AWgeKG9pJC9rzmUofmPFP771FehPw7R11xQxTcdXCF2HfxyH3W7oHT") // opcional, mas recomendado
    @GET("v1/images/search")
    Call<List<Cat
            >> getRandomCatImage();
}

