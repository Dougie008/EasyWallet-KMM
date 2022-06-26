package com.easy.wallet.viewmodels

import com.easy.wallet.models.TokenAsset
import com.easy.wallet.repositories.AssetsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssetsViewModel(
    private val assetsRepository: AssetsRepository
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
            val tokenAssets = assetsRepository.loadAssets()
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