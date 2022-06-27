package com.easy.wallet.domain.models

data class TransactionPlan(
    val amount: Long,
    val to: String,
    val memo: String? = null,
    val contract: String? = null,
    val payload: String? = null
)
