package com.easy.wallet.domain.models

data class Transaction(
    val txHash: String,
    val value: String,
    val from: String,
    val to: String,
    val timeStamp: String,
    val isReceive: Boolean,
    val inputData: String
)
