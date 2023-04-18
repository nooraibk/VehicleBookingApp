package com.example.vehiclebookingapp.driver.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {

    private var retrofitService: RetrofitInstanceDriver? = null
    fun getInstance() : RetrofitInstanceDriver {
        val loggingInterceptor = HttpLoggingInterceptor()
        val level =  HttpLoggingInterceptor.Level.BODY
        loggingInterceptor.setLevel(level)

        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()

        if (retrofitService == null){
            val retrofit by lazy {
                Retrofit.Builder()
                    .baseUrl("https://wordpress-961811-3357994.cloudwaysapps.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
                    .create(RetrofitInstanceDriver::class.java)
            }
            retrofitService = retrofit
        }
        return retrofitService!!
    }
}