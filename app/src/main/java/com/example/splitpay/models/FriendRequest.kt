package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class FriendRequest(

	@field:SerializedName("data")
	val data: List<DataItem1>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItem1(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
