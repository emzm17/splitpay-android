package com.example.splitpay.api

import com.example.splitpay.models.ExpenseResponse
import com.example.splitpay.models.GroupResponse
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

  @POST("users/signup")
  suspend fun signup(@Body userSignupRequest: UserSignupRequest) : Response<UserSignupResponse>


  @POST("users/signin")
  suspend fun signin(@Body userSigninRequest: UserSigninRequest) : Response<UserSigninResponse>


  @GET("users/")
  suspend fun getAllUser():Response<ArrayList<UserResponse>>

  @GET("users/friends")
  suspend fun getAllFriends():Response<ArrayList<UserResponse>>

  @GET("users/user-involved-groups/")
  suspend fun getAllUserGroups():Response<ArrayList<GroupResponse>>

  @GET("expenses/{id}")
  suspend fun getAllExpenseGroup(@Path("id")id:Int):Response<ArrayList<ExpenseResponse>>


  @GET("groups/")
  suspend fun getAllGroups():Response<ArrayList<GroupResponse>>

  @GET("groups/")
  suspend fun getparticularGroup(@Path("id")id:Int):Response<GroupResponse>








}