package com.example.splitpay.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.splitpay.api.RetrofitHelper2.api2
import com.example.splitpay.models.UserResponse
import com.example.splitpay.utils.NetworkResult
import org.json.JSONObject

object AuthUserRepository {

    private val _getAllUserLiveData=MutableLiveData<NetworkResult<ArrayList<UserResponse>>>()
    val getAllUserLiveData:LiveData<NetworkResult<ArrayList<UserResponse>>>
        get() = _getAllUserLiveData

    private val _getUserLiveData=MutableLiveData<NetworkResult<ArrayList<UserResponse>>>()
    val getUserLiveData:LiveData<NetworkResult<ArrayList<UserResponse>>>
        get() = _getUserLiveData


    suspend fun getAllUsers(){
         _getAllUserLiveData.postValue(NetworkResult.Loading())
         val response=api2.getAllUser()
        if (response.isSuccessful && response.body() != null) {
           _getAllUserLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getAllUserLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _getAllUserLiveData.postValue(NetworkResult.Error("something went wrong"))
        }
    }

    suspend fun getUsers(){
        _getUserLiveData.postValue(NetworkResult.Loading())
        val response=api2.getAllFriends()
        if (response.isSuccessful && response.body() != null) {
            _getUserLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getUserLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _getUserLiveData.postValue(NetworkResult.Error("something went wrong"))
        }
    }
}