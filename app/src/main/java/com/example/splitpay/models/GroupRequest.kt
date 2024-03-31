package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class GroupRequest(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("users_id")
	val usersId: List<Int?>? = null,

	@field:SerializedName("created_by")
	val createdBy: Int? = null
)
