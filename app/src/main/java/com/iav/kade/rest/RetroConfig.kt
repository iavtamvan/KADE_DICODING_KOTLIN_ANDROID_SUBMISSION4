package com.iav.kade.rest

import com.google.gson.GsonBuilder
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory

object RetroConfig {

    private const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"

    fun provideApi(): ApiService {
        val gson = GsonBuilder().create()
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService::class.java)
    }
}