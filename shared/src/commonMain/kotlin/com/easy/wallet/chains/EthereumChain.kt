package com.easy.wallet.chains

import com.easy.wallet.domain.enums.Network
import com.easy.wallet.domain.models.Transaction
import com.easy.wallet.domain.models.TransactionPlan
import com.easy.wallet.exs.clearHexPrefix
import com.easy.wallet.models.dto.BaseRpcRequest
import com.easy.wallet.models.dto.BaseRpcResponseDto
import com.easy.wallet.models.dto.CallParameter
import com.easy.wallet.models.dto.StringParameter
import com.easy.wallet.remote.HttpRoutes
import com.russhwolf.settings.Settings
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class EthereumChain(
    private val appSetting: Settings,
    private val httpClient: HttpClient
) : IChain {
    override suspend fun sign(
        plan: TransactionPlan,
        block: () -> String
    ): String {
        val rawData = block.invoke()
        return rawData
    }

    override fun address(block: () -> String): String {
        return block.invoke()
    }

    override suspend fun balance(address: String, contract: String?): Long = withContext(Dispatchers.Default) {
        try {
            val reqBody = if (contract.isNullOrEmpty()) {
                BaseRpcRequest(
                    id = 1,
                    jsonrpc = "2.0",
                    method = "eth_getBalance",
                    params = listOf(
                        StringParameter(address),
                        StringParameter("latest")
                    )
                )
            } else {
                BaseRpcRequest(
                    id = 1,
                    jsonrpc = "2.0",
                    method = "eth_call",
                    params = listOf(
                        CallParameter(
                            from = address,
                            to = contract,
                            data = "0x70a08231000000000000000000000000${address.clearHexPrefix()}"
                        ),
                        StringParameter("latest")
                    )
                )
            }
            val response: BaseRpcResponseDto<String> = httpClient.post {
                url(rpc())
                setBody(reqBody)
            }.body()
            response.result.clearHexPrefix().toLong(16)
        } catch (e: Exception) {
            0L
        }
    }

    override suspend fun transactions(): Result<Transaction> {
        TODO("Not yet implemented")
    }

    override suspend fun broadcast(rawData: String): Result<String> {
        TODO("Not yet implemented")
    }

    private fun chainId(): Int {
        return appSetting.getInt("", 1)
    }

    private fun rpc(): String {
        return when(chainId()) {
            Network.MAIN.id -> HttpRoutes.MAINNET_RPC
            Network.ROPSTEN.id -> HttpRoutes.ROPSTEN_RPC
            Network.RINKEBY.id -> HttpRoutes.RINKEBY_RPC
            else -> HttpRoutes.MAINNET_RPC
        }
    }

    private fun explorerUrl(): String {
        return when(chainId()) {
            Network.MAIN.id -> HttpRoutes.MAINNET_EXPLORER
            Network.ROPSTEN.id -> HttpRoutes.ROPSTEN_EXPLORER
            Network.RINKEBY.id -> HttpRoutes.RINKEBY_EXPLORER
            else -> HttpRoutes.MAINNET_RPC
        }
    }
}