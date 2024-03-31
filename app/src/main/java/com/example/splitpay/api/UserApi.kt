package com.example.splitpay.api

import com.example.splitpay.models.ExpenseRequest
import com.example.splitpay.models.ExpenseResponse
import com.example.splitpay.models.GroupRequest
import com.example.splitpay.models.GroupResponse
import com.example.splitpay.models.User
import com.example.splitpay.models.UserResponse
import com.example.splitpay.models.UserSigninRequest
import com.example.splitpay.models.UserSigninResponse
import com.example.splitpay.models.UserSignupRequest
import com.example.splitpay.models.UserSignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {



  //users route
  @POST("users/signup")
  suspend fun signup(@Body userSignupRequest: UserSignupRequest) : Response<UserSignupResponse>
  @POST("users/signin")
  suspend fun signin(@Body userSigninRequest: UserSigninRequest) : Response<UserSigninResponse>
  @GET("users/{id}")
  suspend fun getparticularUser(@Path("id")id:Int):Response<UserResponse>
  @GET("users/")
  suspend fun getAllUser():Response<ArrayList<UserResponse>>





  @GET("friends/")
  suspend fun getAllFriends():Response<ArrayList<User>>




  @GET("groups/")
  suspend fun getAllUserGroups():Response<ArrayList<GroupResponse>>

  @GET("groups/all")
  suspend fun getAllGroups():Response<ArrayList<GroupResponse>>
  @GET("groups/{id}")
  suspend fun getparticularGroup(@Path("id")id:Int):Response<GroupResponse>

  @POST("groups/create")
  suspend fun createGroup(@Body groupRequest: GroupRequest) : Response< GroupRequest>





  @GET("expenses/{id}")
  suspend fun getAllExpenseGroup(@Path("id")id:Int):Response<ArrayList<ExpenseResponse>>

  @GET("expenses/particular/{id}")
  suspend fun getparticularExpense(@Path("id")id:Int):Response<ArrayList<ExpenseResponse>>


  @POST("expenses/create")
  suspend fun createExpense(@Body expenseRequest: ExpenseRequest):Response<ExpenseRequest>


















}