package com.example.android.booksearchapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookApi {

    @GET("volumes")
    Call<Items> getBooks(@Query("q") String q, @Query("maxResults") int maxResults);
}

