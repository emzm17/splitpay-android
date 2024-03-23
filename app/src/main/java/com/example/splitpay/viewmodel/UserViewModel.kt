package com.example.splitpay.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitpay.models.ExpenseResponse
import com.example.splitpay.models.GroupResponse
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
    val _getAllUserGroup:LiveData<NetworkResult<ArrayList<GroupResponse>>>
        get() = AuthUserRepository.getAllUserGroupsLiveData

    val _getAllExpense:LiveData<NetworkResult<ArrayList<ExpenseResponse>>>
        get() = AuthUserRepository.getAllExpenseLiveData

    val _getAllGroup:LiveData<NetworkResult<ArrayList<GroupResponse>>>
        get() = AuthUserRepository.getAllGroupsLiveData

    val _getparticularGroup:LiveData<NetworkResult<GroupResponse>>
        get()=AuthUserRepository.getparticularGroup


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
    fun getGroups(){
        viewModelScope.launch {
             AuthUserRepository.getGroups()
        }
    }

    fun getExpenses(id:Int){
        viewModelScope.launch {
             AuthUserRepository.getExpense(id)
        }
    }

    fun getAllGroups(){
        viewModelScope.launch {
             AuthUserRepository.getallGroups()
        }
    }

    fun getparticularGroup(id:Int){
        viewModelScope.launch {
             AuthUserRepository.getparticularGroups(id)
        }
    }

}