package com.easy.wallet.android

import android.app.Application
import com.easy.wallet.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EasyApplication: Application() {
    init {
        System.loadLibrary("TrustWalletCore")
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EasyApplication)
            androidLogger()
            modules(appModule())
        }
    }
}