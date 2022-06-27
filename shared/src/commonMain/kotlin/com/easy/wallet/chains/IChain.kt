package com.easy.wallet.chains

import com.easy.wallet.domain.models.Transaction
import com.easy.wallet.domain.models.TransactionPlan
import com.ionspin.kotlin.bignum.integer.BigInteger

internal interface IChain {
    suspend fun sign(plan: TransactionPlan, block: () -> String): String
    fun address(block: () -> String): String
    suspend fun balance(address: String, contract: String?): BigInteger
    suspend fun transactions(): Result<Transaction>
    suspend fun broadcast(rawData: String): Result<String>
}