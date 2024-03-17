package com.example.splitpay.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitpay.models.UserSigninRequest
import com.example.splitpay.models.UserSigninResponse
import com.example.splitpay.models.UserSignupRequest
import com.example.splitpay.models.UserSignupResponse
import com.example.splitpay.repository.UserRepository
import com.example.splitpay.utils.NetworkResult
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    val _userSignupResponseLiveData: LiveData<NetworkResult<UserSignupResponse>>
        get() = UserRepository.userSignupResponseLiveData
    val _userSigninResponseLiveData: LiveData<NetworkResult<UserSigninResponse>>
        get() = UserRepository.userSigninResponseLiveData


    fun registerUser(userSignupRequest: UserSignupRequest) {
        viewModelScope.launch {
            UserRepository.registerUser(userSignupRequest)
        }
    }

    fun loginUser(userSigninRequest: UserSigninRequest) {
        viewModelScope.launch {
            UserRepository.loginUser(userSigninRequest)

        }
    }

    fun validateCreds(name:String,email: String,password:String,isLogin:Boolean):Pair<Boolean,String>{
        var result=Pair(true,"")
        if(   ( !isLogin && TextUtils.isEmpty(name) ) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
             result=Pair(false,"Please provide the credentials")
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            result=Pair(false,"Please provide valid email")
        }
        else if(( !isLogin && password.length<5)){
            result=Pair(false,"Password length should greater than 5")
        }
        return result
    }


}