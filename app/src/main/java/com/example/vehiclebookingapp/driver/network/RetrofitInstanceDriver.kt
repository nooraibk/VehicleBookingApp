package com.example.vehiclebookingapp.driver.network

import com.example.vehiclebookingapp.driver.data.RetrofitServiceDriver
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceDriver {

    private const val url = "https://carapi.cricdigital.com/api/"



    private var retrofitServiceDriver: RetrofitServiceDriver? = null
    fun getInstance(): RetrofitServiceDriver {

        val loggingInterceptor = HttpLoggingInterceptor()
        val level =  HttpLoggingInterceptor.Level.BODY
        loggingInterceptor.setLevel(level)

        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()

        if (retrofitServiceDriver == null) {
            val retrofit by lazy {
                Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
                    .create(RetrofitServiceDriver::class.java)
            }
            retrofitServiceDriver = retrofit
        }
        return retrofitServiceDriver!!
    }
}