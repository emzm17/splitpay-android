package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class ExpenseRequest(

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("group_id")
	val groupId: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("payer_id")
	val payerId: Int? = null
)
