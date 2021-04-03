package com.example.coroutineretrofit1.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        val BASE_URL="https://api.github.com/search/"

        fun getRetroInstance():Retrofit{
            return Retrofit.Builder().baseUrl(BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}