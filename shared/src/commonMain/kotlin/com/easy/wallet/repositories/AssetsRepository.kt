package com.easy.wallet.repositories

import com.easy.wallet.WalletDatabase
import com.easy.wallet.chains.DefaultChain
import com.easy.wallet.chains.EthereumChain
import com.easy.wallet.chains.IChain
import com.easy.wallet.domain.enums.AssetTag
import com.easy.wallet.domain.enums.NetworkChain
import com.easy.wallet.models.TokenAsset
import com.easy.wallet.models.dto.BaseResponseDto
import com.easy.wallet.models.dto.CoinConfigDto
import com.easy.wallet.models.mapper.toTokenAsset
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

private val default_coins = listOf(
    CoinConfigDto(
        chain = NetworkChain.Ethereum,
        coinSymbol = "ETH",
        contractAddress = null,
        decimal = 18,
        iconUrl = "https://easywallet.s3.amazonaws.com/wallet-icons/ethereum.png",
        tag = null
    ),
    CoinConfigDto(
        chain = NetworkChain.Ethereum,
        coinSymbol = "DAI",
        contractAddress = "0x6b175474e89094c44da98b954eedeac495271d0f",
        decimal = 18,
        iconUrl = "https://easywallet.s3.amazonaws.com/wallet-icons/DAIxxxhdpi.png",
        tag = AssetTag.ERC20
    ),
    CoinConfigDto(
        chain = NetworkChain.Ethereum,
        coinSymbol = "CRO",
        contractAddress = "0xa0b73e1ff0b80914ab6fe0444e65848c4c34450b",
        decimal = 8,
        iconUrl = "https://easywallet.s3.amazonaws.com/wallet-icons/cro.png",
        tag = AssetTag.ERC20
    ),
    CoinConfigDto(
        chain = NetworkChain.Solana,
        coinSymbol = "SOL",
        contractAddress = null,
        decimal = 9,
        iconUrl = "https://cryptologos.cc/logos/solana-sol-logo.png",
        tag = null
    ),
)

class AssetsRepository(
    private val httpClient: HttpClient,
    private val appSettings: Settings,
    sqlDriver: SqlDriver
) {
    private val database = WalletDatabase(sqlDriver)
    private val mutex = Mutex()
    private val chains: MutableMap<NetworkChain, IChain> = mutableMapOf()

    suspend fun loadAssets(): List<TokenAsset> {
        return kotlin.runCatching {
            val responseDto: BaseResponseDto<List<CoinConfigDto>> = httpClient.get(
                urlString = "http://141.164.63.231:8080/currencies"
            ).body()
            responseDto.data
        }.getOrElse {
            default_coins
        }.map { item ->
            mutex.withLock {
                syncChains(item)
            }
            item.toTokenAsset()
        }
    }

    private fun syncChains(coinConfigDto: CoinConfigDto) {
        val chain = coinConfigDto.chain
        if (chains[chain] == null) {
            when {
                chain == NetworkChain.Ethereum || coinConfigDto.tag == AssetTag.ERC20 -> {
                    chains[chain] = EthereumChain(appSettings, httpClient)
                }
                else -> Unit
            }
        }
    }

    internal fun find(chain: NetworkChain): IChain {
        return chains[chain] ?: DefaultChain
    }

    fun insertToken() {
        database.assetsQueries.transaction {
            database.assetsQueries.insertTokenAsset(
                com.easy.wallet.TokenAssetEntity(
                    slug = "hello2",
                    symbol = "world2",
                    icon = "icon_url2",
                    decimal = 8
                )
            )
        }
    }

    fun loadLocalTokenAsset(): Flow<String> {
        return database.assetsQueries.selectAll().asFlow().map {
            it.executeAsList().toString()
        }
    }
}