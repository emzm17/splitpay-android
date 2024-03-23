package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class GroupResponse(

//	@field:SerializedName("GroupResponse")
//	val groupResponse: List<GroupResponseItem?>? = null

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("users_id")
	val usersId: List<Int?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("created_by")
	val createdBy: Int? = null
)

//data class GroupResponseItem(
//
//	@field:SerializedName("created")
//	val created: String? = null,
//
//	@field:SerializedName("name")
//	val name: String? = null,
//
//	@field:SerializedName("users_id")
//	val usersId: List<Int?>? = null,
//
//	@field:SerializedName("id")
//	val id: Int? = null,
//
//	@field:SerializedName("created_by")
//	val createdBy: Int? = null
//)
