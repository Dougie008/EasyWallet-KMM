package com.easy.wallet

import com.easy.wallet.di.appModule
import com.easy.wallet.remote.EasyApi
import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

object KoinHelper: KoinComponent {
    fun api() = getKoin().get<EasyApi>()
}

fun initKoin(
    userDefaults: NSUserDefaults
) {
    startKoin {
        modules(appModule(module {
            single<Settings> {
                AppleSettings(userDefaults)
            }
        }))
    }
}
