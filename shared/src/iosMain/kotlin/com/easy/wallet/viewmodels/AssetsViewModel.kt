package com.easy.wallet.viewmodels

import com.easy.wallet.repositories.AssetsRepository
import com.easy.wallet.repositories.CoinRepository

class AssetsCallbackViewModel(
    assetsRepository: AssetsRepository,
    coinRepository: CoinRepository
) : CallbackViewModel() {
    override val viewModel = AssetsViewModel(assetsRepository = assetsRepository, coinRepository = coinRepository)

    val assetsState = viewModel.assetsState.asCallbacks()

    fun loadLocal() {
        viewModel.loadLocal()
    }
}