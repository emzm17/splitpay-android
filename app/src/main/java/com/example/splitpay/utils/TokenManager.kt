package com.example.splitpay.utils

import android.content.Context
import com.example.splitpay.utils.Constants.TOKEN
import com.example.splitpay.utils.Constants.USER_TOKEN

class TokenManager(context: Context) {
    private var sharedPreferences=context.getSharedPreferences(TOKEN,Context.MODE_PRIVATE)

    fun saveToken(token:String){
         val editor=sharedPreferences.edit()
        editor.putString(USER_TOKEN,token)
    }
    fun getToken(): String? {
         return sharedPreferences.getString(USER_TOKEN,null)
    }
}