package com.example.splitpay.models

data class _Data(
    val email: String,
    val friend_list: List<Int>,
    val name: String,
    val total_amount: Double,
    val total_owe: Double,
    val total_owed: Double,
    val user_id: Int
)