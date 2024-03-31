package com.example.splitpay.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.splitpay.api.RetrofitHelper
import com.example.splitpay.api.RetrofitHelper2.api2
import com.example.splitpay.models.ExpenseRequest
import com.example.splitpay.models.ExpenseResponse
import com.example.splitpay.models.GroupRequest
import com.example.splitpay.models.GroupResponse
import com.example.splitpay.models.User
import com.example.splitpay.models.UserResponse
import com.example.splitpay.utils.NetworkResult
import org.json.JSONObject

object AuthUserRepository {

    private val _getAllUserLiveData=MutableLiveData<NetworkResult<ArrayList<UserResponse>>>()
    val getAllUserLiveData:LiveData<NetworkResult<ArrayList<UserResponse>>>
        get() = _getAllUserLiveData

    private val _getUserLiveData=MutableLiveData<NetworkResult<ArrayList<User>>>()
    val getUserLiveData:LiveData<NetworkResult<ArrayList<User>>>
        get() = _getUserLiveData

    private val _getAllUserGroupsLiveData=MutableLiveData<NetworkResult<ArrayList<GroupResponse>>>()
    val getAllUserGroupsLiveData:LiveData<NetworkResult<ArrayList<GroupResponse>>>
        get() = _getAllUserGroupsLiveData

    private val _getAllExpenseLiveData= MutableLiveData<NetworkResult<ArrayList<ExpenseResponse>>>()
    val getAllExpenseLiveData:LiveData<NetworkResult<ArrayList<ExpenseResponse>>>
        get() = _getAllExpenseLiveData

    private val _getAllGroupsLiveData= MutableLiveData<NetworkResult<ArrayList<GroupResponse>>>()
    val getAllGroupsLiveData:LiveData<NetworkResult<ArrayList<GroupResponse>>>
        get() = _getAllUserGroupsLiveData

    private val _getparticularGroup=MutableLiveData<NetworkResult<GroupResponse>>()
    val getparticularGroup:LiveData<NetworkResult<GroupResponse>>
        get() = _getparticularGroup

    private val _getparticularUser=MutableLiveData<NetworkResult<UserResponse>>()
    val getparticularUser:LiveData<NetworkResult<UserResponse>>
        get() = _getparticularUser

    private val _usercreateGroup=MutableLiveData<NetworkResult<GroupRequest>>()
    val usercreateGroup:LiveData<NetworkResult<GroupRequest>>
        get() = _usercreateGroup

    private val _usercreateExpense=MutableLiveData<NetworkResult<ExpenseRequest>>()
    val usercreateExpense:LiveData<NetworkResult<ExpenseRequest>>
        get() = _usercreateExpense

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

    suspend fun getGroups(){
        _getAllGroupsLiveData.postValue(NetworkResult.Loading())
        val response=api2.getAllUserGroups()
        if (response.isSuccessful && response.body() != null) {
            _getAllUserGroupsLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getAllUserGroupsLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _getAllUserGroupsLiveData.postValue(NetworkResult.Error("something went wrong"))
        }
    }

    suspend fun getExpense(id:Int){
         _getAllExpenseLiveData.postValue(NetworkResult.Loading())
         val response=api2.getAllExpenseGroup(id)
        if (response.isSuccessful && response.body() != null) {
            _getAllExpenseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getAllExpenseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _getAllExpenseLiveData.postValue(NetworkResult.Error("something went wrong"))
        }
    }

    suspend fun getallGroups(){
        _getAllGroupsLiveData.postValue(NetworkResult.Loading())
        val response=api2.getAllGroups()
        if (response.isSuccessful && response.body() != null) {
            _getAllGroupsLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getAllGroupsLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _getAllGroupsLiveData.postValue(NetworkResult.Error("something went wrong"))
        }
    }
    suspend fun getparticularGroups(id: Int){
        _getparticularGroup.postValue(NetworkResult.Loading())
        val response=api2.getparticularGroup(id)
        if (response.isSuccessful && response.body() != null) {
            _getparticularGroup.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getparticularGroup.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _getparticularGroup.postValue(NetworkResult.Error("something went wrong"))
        }
    }
    suspend fun getparticularUser(id: Int){
        _getparticularUser.postValue(NetworkResult.Loading())
        val response=api2.getparticularUser(id)
        if (response.isSuccessful && response.body() != null) {
            _getparticularUser.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getparticularUser.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _getparticularUser.postValue(NetworkResult.Error("something went wrong"))
        }
    }

    suspend fun createGroup(groupRequest: GroupRequest){
        _usercreateGroup.postValue(NetworkResult.Loading())
        val response = api2.createGroup(groupRequest)
        if (response.isSuccessful && response.body() != null) {
            _usercreateGroup.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _usercreateGroup.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _usercreateGroup.postValue(NetworkResult.Error("something went wrong"))
        }
    }
    suspend fun createExpense(expenseRequest: ExpenseRequest){
        _usercreateExpense.postValue(NetworkResult.Loading())
        val response = api2.createExpense(expenseRequest)
        if (response.isSuccessful && response.body() != null) {
            _usercreateExpense.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _usercreateExpense.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _usercreateExpense.postValue(NetworkResult.Error("something went wrong"))
        }
    }

}