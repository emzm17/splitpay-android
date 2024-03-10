package com.example.splitpay.api

import com.example.splitpay.models.UserResponse
import com.example.splitpay.models.UserSigninRequest
import com.example.splitpay.models.UserSigninResponse
import com.example.splitpay.models.UserSignupRequest
import com.example.splitpay.models.UserSignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

  @POST("users/signup")
  suspend fun signup(@Body userSignupRequest: UserSignupRequest) : Response<UserSignupResponse>


  @POST("users/signin")
  suspend fun signin(@Body userSigninRequest: UserSigninRequest) : Response<UserSigninResponse>


  @GET("users/")
  suspend fun getAllUser():Response<List<UserResponse>>

  @GET("users/friends")
  suspend fun getAllFriends():Response<List<UserResponse>>




}