package com.easy.wallet.chains

import com.easy.wallet.domain.models.Transaction
import com.easy.wallet.domain.models.TransactionPlan
import com.easy.wallet.errors.UnSupportChainException
import com.ionspin.kotlin.bignum.integer.BigInteger

internal object DefaultChain : IChain {
    override suspend fun sign(plan: TransactionPlan, block: () -> String): String {
        return ""
    }

    override fun address(block: () -> String): String {
        return ""
    }

    override suspend fun balance(address: String, contract: String?): BigInteger {
        return BigInteger.ZERO
    }

    override suspend fun transactions(): Result<Transaction> {
        return Result.failure(UnSupportChainException())
    }

    override suspend fun broadcast(rawData: String): Result<String> {
        return Result.failure(UnSupportChainException())
    }
}
