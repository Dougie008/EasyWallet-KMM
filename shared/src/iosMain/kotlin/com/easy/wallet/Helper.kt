package com.easy.wallet

import com.easy.wallet.di.appModule
import com.easy.wallet.models.TokenAsset
import com.easy.wallet.remote.EasyApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KoinHelper: KoinComponent {
    private val easyApi: EasyApi by inject()
    fun api(): EasyApi = easyApi
    suspend fun loadAssets(): List<TokenAsset> {
        return easyApi.loadAssets()
    }
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}
