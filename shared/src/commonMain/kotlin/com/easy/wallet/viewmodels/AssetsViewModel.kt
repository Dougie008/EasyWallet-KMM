package com.easy.wallet.viewmodels

import com.easy.wallet.models.TokenAsset
import com.easy.wallet.repositories.AssetsRepository
import com.easy.wallet.repositories.CoinRepository
import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AssetsViewModel(
    private val assetsRepository: AssetsRepository,
    private val coinRepository: CoinRepository
) : ViewModel() {
    private val mutableAssetsViewState: MutableStateFlow<AssetsViewState> = MutableStateFlow(
        AssetsViewState(isLoading = true)
    )
    val assetsState: StateFlow<AssetsViewState> = mutableAssetsViewState

    init {
        observeAssets()
    }

    private fun observeAssets() {
        viewModelScope.launch {
            val tokenAssets = assetsRepository.loadAssets().let {
                val balances = mutableListOf<Deferred<Pair<String, BigInteger>>>()
                coroutineScope {
                    for (item in it) {
                        val balance = async {
                            Pair(
                                item.slug,
                                coinRepository.balance(item.chain, "0x81080a7e991bcdddba8c2302a70f45d6bd369ab5", item.contractAddress)
                            )
                        }
                        balances.add(balance)
                    }
                }
                val balanceResult = balances.awaitAll()
                it.map { item ->
                    val actualBalance = balanceResult.find { it.first == item.slug }?.second ?: BigInteger.ZERO
                    item.copy(balance = actualBalance)
                }
            }

            mutableAssetsViewState.update {
                AssetsViewState(isLoading = false, tokenAssets = tokenAssets)
            }
        }
    }

    fun loadLocal() {
        viewModelScope.launch {
            assetsRepository.loadLocalTokenAsset().collect { result ->
                mutableAssetsViewState.update {
                    it.copy(localContent = result)
                }
            }
        }
    }
}

data class AssetsViewState(
    val tokenAssets: List<TokenAsset> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val localContent: String = ""
)