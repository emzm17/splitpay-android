package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class GroupRequest(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("users")
	val users: List<UsersItem?>? = null
)

