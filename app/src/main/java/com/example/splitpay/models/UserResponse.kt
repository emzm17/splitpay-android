package com.example.splitpay.models

data class UserResponse(
	val data: _Data? = null,
	val message: String? = null,
	val status: String? = null
)

data class _Data(
	val totalOwed: String? = null,
	val friendList: List<Int?>? = null,
	val userId: Int? = null,
	val totalAmount: String? = null,
	val name: String? = null,
	val email: String? = null,
	val totalOwe: String? = null
)

