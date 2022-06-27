package com.easy.wallet.repositories

import com.easy.wallet.domain.enums.NetworkChain
import com.easy.wallet.domain.models.TransactionPlan

class CoinRepository(
    private val assetsRepository: AssetsRepository
) {
    suspend fun signTransaction(chain: NetworkChain, plan: TransactionPlan, block: () -> String): String {
        return assetsRepository.find(chain).sign(plan, block)
    }

    suspend fun balance(chain: NetworkChain, address: String, contract: String?): Long {
        return assetsRepository.find(chain).balance(address, contract)
    }

    suspend fun broadcastTransaction(chain: NetworkChain, data: String): Result<String> {
        return assetsRepository.find(chain).broadcast(data)
    }
}