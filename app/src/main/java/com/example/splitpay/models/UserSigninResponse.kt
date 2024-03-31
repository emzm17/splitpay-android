package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class UserSigninResponse(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
