package com.example.splitpay.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.splitpay.api.RetrofitHelper.api
import com.example.splitpay.models.UserSigninRequest
import com.example.splitpay.models.UserSigninResponse
import com.example.splitpay.models.UserSignupRequest
import com.example.splitpay.models.UserSignupResponse
import com.example.splitpay.utils.Constants.TAG
import com.example.splitpay.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response


object UserRepository {
    private val _userSignupLiveData = MutableLiveData<NetworkResult<UserSignupResponse>>()
    val userSignupResponseLiveData: LiveData<NetworkResult<UserSignupResponse>>
        get() = _userSignupLiveData
    private val _userSigninLiveData = MutableLiveData<NetworkResult<UserSigninResponse>>()
    val userSigninResponseLiveData: LiveData<NetworkResult<UserSigninResponse>>
        get() = _userSigninLiveData

    suspend fun registerUser(userSignupRequest: UserSignupRequest) {
        _userSignupLiveData.postValue(NetworkResult.Loading())
        val response = api.signup(userSignupRequest)
        if (response.isSuccessful && response.body() != null) {
            _userSignupLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userSignupLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _userSignupLiveData.postValue(NetworkResult.Error("something went wrong"))
        }
    }


    suspend fun loginUser(userSigninRequest: UserSigninRequest) {
        _userSigninLiveData.postValue(NetworkResult.Loading())
        val response = api.signin(userSigninRequest)
        if (response.isSuccessful && response.body() != null) {
            _userSigninLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userSigninLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _userSigninLiveData.postValue(NetworkResult.Error("something went wrong"))
        }
    }

}