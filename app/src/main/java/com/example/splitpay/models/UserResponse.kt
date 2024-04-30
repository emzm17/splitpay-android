package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("data")
	val data: _Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class _Data(

	@field:SerializedName("total_owed")
	val totalOwed: String? = null,

	@field:SerializedName("friend_list")
	val friendList: List<FriendListItem?>? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("total_amount")
	val totalAmount: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("total_owe")
	val totalOwe: String? = null
)

data class FriendListItem(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
