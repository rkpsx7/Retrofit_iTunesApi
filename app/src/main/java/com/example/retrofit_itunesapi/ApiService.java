package com.example.retrofit_itunesapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search")
    Call<ResponseiTunes> getSongs(@Query("term") String term);
}
