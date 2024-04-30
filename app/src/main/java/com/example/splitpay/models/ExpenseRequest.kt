package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class ExpenseRequest(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("group_id")
	val groupId: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("payer")
	val payer: List<_PayerItem?>? = null
)

data class _PayerItem(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
