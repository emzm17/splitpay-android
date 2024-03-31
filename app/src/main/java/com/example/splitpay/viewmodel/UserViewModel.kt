package com.example.splitpay.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitpay.models.ExpenseRequest
import com.example.splitpay.models.ExpenseResponse
import com.example.splitpay.models.GroupRequest
import com.example.splitpay.models.GroupResponse
import com.example.splitpay.models.User
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
    val _getUser: LiveData<NetworkResult<ArrayList<User>>>
        get() = AuthUserRepository.getUserLiveData
    val _getAllUserGroup:LiveData<NetworkResult<ArrayList<GroupResponse>>>
        get() = AuthUserRepository.getAllUserGroupsLiveData

    val _getAllExpense:LiveData<NetworkResult<ArrayList<ExpenseResponse>>>
        get() = AuthUserRepository.getAllExpenseLiveData

    val _getAllGroup:LiveData<NetworkResult<ArrayList<GroupResponse>>>
        get() = AuthUserRepository.getAllGroupsLiveData

    val _getparticularGroup:LiveData<NetworkResult<GroupResponse>>
        get()=AuthUserRepository.getparticularGroup
    val _getparticularUser:LiveData<NetworkResult<UserResponse>>
        get()=AuthUserRepository.getparticularUser

    val _createGroupLiveData:LiveData<NetworkResult<GroupRequest>>
        get() = AuthUserRepository.usercreateGroup

    val _createExpenseLiveData:LiveData<NetworkResult<ExpenseRequest>>
        get() = AuthUserRepository.usercreateExpense

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

    fun getparticularUser(id:Int){
        viewModelScope.launch {
             AuthUserRepository.getparticularUser(id)
        }
    }

    fun createGroup(groupRequest: GroupRequest){
        viewModelScope.launch {
             AuthUserRepository.createGroup(groupRequest)
        }
    }

    fun createExpense(expenseRequest: ExpenseRequest){
        viewModelScope.launch {
            AuthUserRepository.createExpense(expenseRequest)
        }
    }

}