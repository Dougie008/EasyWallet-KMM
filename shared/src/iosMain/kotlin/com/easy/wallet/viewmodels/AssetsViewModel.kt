package com.easy.wallet.viewmodels

import com.easy.wallet.repositories.AssetsRepository

class AssetsCallbackViewModel(
    assetsRepository: AssetsRepository
) : CallbackViewModel() {
    override val viewModel = AssetsViewModel(assetsRepository = assetsRepository)

    val assetsState = viewModel.assetsState.asCallbacks()

    fun loadLocal() {
        viewModel.loadLocal()
    }
}