package com.example.coroutineretrofit1.network

import com.example.coroutineretrofit1.model.RecylerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("repositories")
    suspend fun getDataFromAPI(@Query("q") query: String): RecylerList
}