package com.easy.wallet

import com.easy.wallet.di.appModule
import com.easy.wallet.models.TokenAsset
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KoinHelper: KoinComponent {
    private val greeting: Greeting by inject()
    suspend fun loadAssets(): List<TokenAsset> {
        return greeting.getHtml()
    }
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}
