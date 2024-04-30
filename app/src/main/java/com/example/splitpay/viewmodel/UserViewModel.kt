package com.example.splitpay.viewmodel

import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitpay.models.DataItem
import com.example.splitpay.models.ExpenseRequest
import com.example.splitpay.models.ExpenseResponse
import com.example.splitpay.models.FriendRequest
import com.example.splitpay.models.FriendRequestResponse
import com.example.splitpay.models.GroupRequest
import com.example.splitpay.models.GroupResponse
import com.example.splitpay.models.SettlementResponse
import com.example.splitpay.models.User
import com.example.splitpay.models.UserResponse
import com.example.splitpay.models.UserSigninResponse
import com.example.splitpay.models.UserSignupRequest
import com.example.splitpay.models.UserSignupResponse
import com.example.splitpay.repository.AuthUserRepository
import com.example.splitpay.repository.UserRepository
import com.example.splitpay.utils.NetworkResult
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

    val _getAllUser: LiveData<NetworkResult<User>>
        get() = AuthUserRepository.getAllUserLiveData
    val _getUser: LiveData<NetworkResult<User>>
        get() = AuthUserRepository.getUserLiveData
    val _getAllUserGroup:LiveData<NetworkResult<GroupResponse>>
        get() = AuthUserRepository.getAllUserGroupsLiveData

    val _getAllExpense:LiveData<NetworkResult<ExpenseResponse>>
        get() = AuthUserRepository.getAllExpenseLiveData

    val _getparticularExpense:LiveData<NetworkResult<ExpenseResponse>>
        get() = AuthUserRepository.getparticularExpenseLiveData

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

    val _updateInfo:LiveData<NetworkResult<UserSignupResponse>>
        get() = AuthUserRepository.userupdate

    val _sendfriendRequest:LiveData<NetworkResult<FriendRequestResponse>>
        get() = AuthUserRepository.friendrequest

    val _getfriendRequest:LiveData<NetworkResult<FriendRequest>>
        get() = AuthUserRepository.getfriendrequest

    val _acceptfriendRequest:LiveData<NetworkResult<FriendRequestResponse>>
        get() = AuthUserRepository.acceptfriend

    val _getsettlement:LiveData<NetworkResult<SettlementResponse>>
        get() = AuthUserRepository.settlement

    fun getAllUsers(){
         viewModelScope.launch {
             AuthUserRepository.getAllUsers()
         }

    }

    fun getFriends(){
        viewModelScope.launch {
            AuthUserRepository.getFriends()
        }
    }
    fun getGroups(){
        viewModelScope.launch {
             AuthUserRepository.getuserGroups()
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

    fun getparticularExpense(expenseId:Int){
        viewModelScope.launch {
             AuthUserRepository.getParticularExpense(expenseId)
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

    fun updateInfo(userSignupRequest: UserSignupRequest){
        viewModelScope.launch {
             AuthUserRepository.updateInfo(userSignupRequest)
        }
    }

    fun sendFriendRequest(userId:Int){
         viewModelScope.launch {
              AuthUserRepository.sendFriendRequest(userId)
         }
    }

    fun acceptFriendRequest(userId: Int){
        viewModelScope.launch {
             AuthUserRepository.acceptFriendRequest(userId)
        }
    }

    fun getFriendRequest(){
        viewModelScope.launch {
             AuthUserRepository.getFriendRequest()
        }
    }


    fun getsettlement(groupId:Int){
        viewModelScope.launch {
            AuthUserRepository.getsettlement(groupId)
        }
    }

    fun validateInput(name:String,email:String,password:String):Pair<Boolean,String>{
        var result=Pair(true,"")
        if(   TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            result=Pair(false,"fill all the parameter")
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            result=Pair(false,"Please provide valid email")
        }
        else if( password.length<5){
            result=Pair(false,"Password length should greater than 5")
        }
        return result
    }



}