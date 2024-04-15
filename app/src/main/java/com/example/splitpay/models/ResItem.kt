package com.example.splitpay.models

import com.google.gson.annotations.SerializedName

data class ResItem(

    @field:SerializedName("payee")
    val payee: Int? = null,

    @field:SerializedName("amount")
    val amount: Int? = null,

    @field:SerializedName("payer")
    val payer: Int? = null
)
