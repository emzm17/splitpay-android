package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class SettlementResponse(

	@field:SerializedName("res")
	val res: List<ResItem?>? = null
)

