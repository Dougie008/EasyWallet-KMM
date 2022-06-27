package com.easy.wallet.viewmodels

import com.easy.wallet.models.TokenAsset
import com.easy.wallet.repositories.AssetsRepository
import com.easy.wallet.repositories.CoinRepository

class AssetsCallbackViewModel(
    assetsRepository: AssetsRepository,
    coinRepository: CoinRepository
) : CallbackViewModel() {
    override val viewModel = AssetsViewModel(assetsRepository = assetsRepository, coinRepository = coinRepository)

    val assetsState = viewModel.assetsState.asCallbacks()

    fun inject(injectAddress: (List<TokenAsset>) -> List<TokenAsset>) {
         viewModel.inject {
             injectAddress.invoke(it)
         }
    }

    fun loadLocal() {
        viewModel.loadLocal()
    }
}