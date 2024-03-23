package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class ExpenseResponse(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("group_id")
	val groupId: Int? = null,

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("payer_id")
	val payerId: Int? = null,

	@field:SerializedName("expense_id")
	val expenseId: Int? = null
)
