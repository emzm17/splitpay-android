package com.example.splitpay.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitpay.models.UserResponse
import com.example.splitpay.models.UserSigninResponse
import com.example.splitpay.models.UserSignupResponse
import com.example.splitpay.repository.AuthUserRepository
import com.example.splitpay.repository.UserRepository
import com.example.splitpay.utils.NetworkResult
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

    val _getAllUser: LiveData<NetworkResult<ArrayList<UserResponse>>>
        get() = AuthUserRepository.getAllUserLiveData
    val _getUser: LiveData<NetworkResult<ArrayList<UserResponse>>>
        get() = AuthUserRepository.getUserLiveData



    fun getAllUsers(){
         viewModelScope.launch {
             AuthUserRepository.getAllUsers()
         }
    }

    fun getUsers(){
        viewModelScope.launch {
            AuthUserRepository.getUsers()
        }
    }

}