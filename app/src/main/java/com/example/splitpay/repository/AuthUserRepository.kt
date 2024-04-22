package com.example.splitpay.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.splitpay.api.RetrofitHelper
import com.example.splitpay.api.RetrofitHelper2.api2
import com.example.splitpay.models.ExpenseRequest
import com.example.splitpay.models.ExpenseResponse
import com.example.splitpay.models.FriendRequestResponse
import com.example.splitpay.models.GroupRequest
import com.example.splitpay.models.GroupResponse
import com.example.splitpay.models.User
import com.example.splitpay.models.UserResponse
import com.example.splitpay.models.UserSignupRequest
import com.example.splitpay.models.UserSignupResponse
import com.example.splitpay.utils.Constants.TAG
import com.example.splitpay.utils.NetworkResult
import org.json.JSONObject

object AuthUserRepository {

    private val _getAllUserLiveData=MutableLiveData<NetworkResult<User>>()
    val getAllUserLiveData:LiveData<NetworkResult<User>>
        get() = _getAllUserLiveData

    private val _getUserLiveData=MutableLiveData<NetworkResult<User>>()
    val getUserLiveData:LiveData<NetworkResult<User>>
        get() = _getUserLiveData



    private val _getAllUserGroupsLiveData=MutableLiveData<NetworkResult<GroupResponse>>()
    val getAllUserGroupsLiveData:LiveData<NetworkResult<GroupResponse>>
        get() = _getAllUserGroupsLiveData


    private val _getparticularExpenseLiveData= MutableLiveData<NetworkResult<ExpenseResponse>>()
    val getparticularExpenseLiveData:LiveData<NetworkResult<ExpenseResponse>>
        get() = _getparticularExpenseLiveData


    private val _getAllExpenseLiveData= MutableLiveData<NetworkResult<ExpenseResponse>>()
    val getAllExpenseLiveData:LiveData<NetworkResult<ExpenseResponse>>
        get() =_getAllExpenseLiveData



    private val _getAllGroupsLiveData= MutableLiveData<NetworkResult<ArrayList<GroupResponse>>>()
    val getAllGroupsLiveData:LiveData<NetworkResult<ArrayList<GroupResponse>>>
        get() = _getAllGroupsLiveData

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

    private val _userupdate=MutableLiveData<NetworkResult<UserSignupResponse>>()
    val userupdate:LiveData<NetworkResult<UserSignupResponse>>
        get() = _userupdate


    private val _sendFriendrequest= MutableLiveData<NetworkResult<FriendRequestResponse>>()
    val friendrequest:LiveData<NetworkResult<FriendRequestResponse>>
        get() = _sendFriendrequest


    private val _acceptFriendrequest=MutableLiveData<NetworkResult<FriendRequestResponse>>()
    val acceptfriend:LiveData<NetworkResult<FriendRequestResponse>>
        get() = _acceptFriendrequest


    private val _getFriendrequest= MutableLiveData<NetworkResult<User>>()
    val getfriendrequest:LiveData<NetworkResult<User>>
        get() = _getFriendrequest


    suspend fun getAllUsers(){
         _getAllUserLiveData.postValue(NetworkResult.Loading())
         val response=api2.getAllUser()

        if (response.isSuccessful && response.body() != null) {
           _getAllUserLiveData.postValue(NetworkResult.Success(response.body()!!))
            Log.i("madar",response.body().toString())

        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getAllUserLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))

        } else {
            _getAllUserLiveData.postValue(NetworkResult.Error("something went wrong"))

        }
    }

    suspend fun getFriends(){
        _getUserLiveData.postValue(NetworkResult.Loading())
        val response=api2.getAllFriends()
        if (response!!.isSuccessful && response.body() != null) {
            _getUserLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getUserLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _getUserLiveData.postValue(NetworkResult.Error("something went wrong"))
        }
    }

    suspend fun getuserGroups(){
        _getAllUserGroupsLiveData.postValue(NetworkResult.Loading())
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
        Log.i("specific user",response.body().toString())
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


    suspend fun updateInfo(userSignupRequest: UserSignupRequest){
       _userupdate.postValue(NetworkResult.Loading())
        val response = api2.updateUserInfo(userSignupRequest)

        if (response.isSuccessful && response.body() != null) {
            _userupdate.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userupdate.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _userupdate.postValue(NetworkResult.Error("something went wrong"))
        }
    }

    suspend fun sendFriendRequest(userId:Int) {
        _sendFriendrequest.postValue(NetworkResult.Loading())
        val response = api2.sendFriendrequest(userId)
        if (response.isSuccessful && response.body() != null) {
            _sendFriendrequest.postValue(NetworkResult.Success(response.body()!!))

        }
        else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _sendFriendrequest.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _sendFriendrequest.postValue(NetworkResult.Error("something went wrong"))
        }

        }


//    suspend fun getFriendRequest(){
//        _getFriendrequest.postValue(NetworkResult.Loading())
//        val response = api2.getfriendRequest()
//        if(response.isSuccessful && response.body()!=null){
//             _getFriendrequest.postValue(NetworkResult.Success(response.body()!!))
//        }
//        else if( response.isSuccessful && response.body()!=null ){
//            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
//            _getFriendrequest.postValue(NetworkResult.Error(errorObj.getString("message")))
//        }
//        else{
//            _getFriendrequest.postValue(NetworkResult.Error("something went wrong"))
//        }
//    }
    suspend fun acceptFriendRequest(userId: Int){
        _acceptFriendrequest.postValue(NetworkResult.Loading())
        val response = api2.acceptFriendrequest(userId)
        if(response.isSuccessful && response.body()!=null){
            _acceptFriendrequest.postValue(NetworkResult.Success(response.body()!!))
        }
        else if( response.isSuccessful && response.body()!=null ){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _acceptFriendrequest.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _acceptFriendrequest.postValue(NetworkResult.Error("something went wrong"))
        }
    }

    suspend fun getParticularExpense(expenseId:Int){
         _getparticularExpenseLiveData.postValue(NetworkResult.Loading())
         val response=api2.getparticularExpense(expenseId)
        if(response.isSuccessful && response.body()!=null){
            _getparticularExpenseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if( response.isSuccessful && response.body()!=null ){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _getparticularExpenseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _getparticularExpenseLiveData.postValue(NetworkResult.Error("something went wrong"))
        }

     }
}