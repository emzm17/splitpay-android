package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class ExpenseResponse(

	@field:SerializedName("data")
	val data: List<_DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class _DataItem(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("group_id")
	val groupId: Int? = null,

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("expense_id")
	val expenseId: Int? = null,

	@field:SerializedName("payer")
	val payer: List<PayerItem?>? = null
)

data class PayerItem(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
