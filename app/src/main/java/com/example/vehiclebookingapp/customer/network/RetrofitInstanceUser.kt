package com.example.vehiclebookingapp.customer.network

import com.example.vehiclebookingapp.customer.data.RetrofitServiceUser
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceUser {

    val url = "https://carapi.cricdigital.com/api/"

    private var retrofitServiceUser: RetrofitServiceUser? = null
    fun getInstance(): RetrofitServiceUser {
        if (retrofitServiceUser == null) {
            val retrofit by lazy {
                Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitServiceUser::class.java)
            }
            retrofitServiceUser = retrofit
        }
        return retrofitServiceUser!!
    }
}