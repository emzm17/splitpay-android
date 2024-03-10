package com.example.splitpay.api

import com.example.splitpay.utils.Constants.BASE_URL
import com.example.splitpay.utils.TokenManager

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api= retrofit.create(UserApi::class.java)
}