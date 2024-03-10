package com.example.splitpay.api

import android.content.Context
import com.example.splitpay.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context):Interceptor {
    private val tokenManager: TokenManager by lazy {
        TokenManager(context)
    }
    override fun intercept(chain: Interceptor.Chain): Response {
         val request=chain.request().newBuilder()
         val token=tokenManager.getToken()
         request.addHeader("Authorization","Bearer $token")
         return chain.proceed(request.build())
    }
}