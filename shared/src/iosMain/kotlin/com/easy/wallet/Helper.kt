package com.easy.wallet

import com.easy.wallet.di.appModule
import com.easy.wallet.models.TokenAsset
import com.easy.wallet.remote.EasyApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

object KoinHelper: KoinComponent {
    fun api() = getKoin().get<EasyApi>()
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}
