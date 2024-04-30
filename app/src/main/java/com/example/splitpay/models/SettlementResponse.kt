package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class SettlementResponse(


	@field:SerializedName("data")
	val data: Res? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ResItem(

	@field:SerializedName("payee")
	val payee: Payee? = null,

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("payer")
	val payer: Payer? = null
)

data class Payee(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class Res(
	@field:SerializedName("res")
	val res: List<ResItem?>? = null
)


data class Payer(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

