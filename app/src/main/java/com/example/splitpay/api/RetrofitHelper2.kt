package com.example.splitpay.api

import android.content.SharedPreferences
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.splitpay.utils.Constants
import com.example.splitpay.utils.Constants.authToken
import com.example.splitpay.utils.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper2{




    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(authToken))
        .build()

        private val retrofit=Retrofit.Builder()
             .baseUrl(Constants.BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .client(okHttpClient)
             .build()

        val api2=retrofit.create(UserApi::class.java)

}