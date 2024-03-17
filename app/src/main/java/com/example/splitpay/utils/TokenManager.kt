package com.example.splitpay.utils

import android.content.Context
import com.example.splitpay.utils.Constants.TOKEN
import com.example.splitpay.utils.Constants.USERID
import com.example.splitpay.utils.Constants.USERNAME
import com.example.splitpay.utils.Constants.USER_EMAIL
import com.example.splitpay.utils.Constants.USER_TOKEN
import com.example.splitpay.utils.Constants.authToken

class TokenManager(context: Context) {
    private var sharedPreferences=context.getSharedPreferences(TOKEN,Context.MODE_PRIVATE)

    fun saveToken(token: String, email: String, userId: Int?, name: String?){
         val editor=sharedPreferences.edit()
        editor.putString(USER_TOKEN,token)
        editor.putString(USERNAME,name)
        editor.putString(USER_EMAIL,email)
        editor.putInt(USERID,userId!!)
        editor.apply()
        authToken=token
    }
    fun getToken(): String? {
         return sharedPreferences.getString(USER_TOKEN, null)
    }
    fun getUsername():String?{
        return sharedPreferences.getString(USERNAME,null)
    }

    fun getUserId():Int{
        return sharedPreferences.getInt(USERID,-1)
    }

    fun getEmail():String?{
        return sharedPreferences.getString(USER_EMAIL,null)
    }

}