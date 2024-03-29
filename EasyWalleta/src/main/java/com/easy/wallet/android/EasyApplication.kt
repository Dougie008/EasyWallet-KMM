package com.easy.wallet.android

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.easy.wallet.di.appModule
import com.easy.wallet.viewmodels.AssetsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class EasyApplication: Application() {
    init {
        System.loadLibrary("TrustWalletCore")
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EasyApplication)
            androidLogger()
            modules(appModule(module {
                single<SharedPreferences> {
                    get<Context>().getSharedPreferences("EASYWALLET_SETTINGS", Context.MODE_PRIVATE)
                }
                viewModel {
                    AssetsViewModel(get(), get())
                }
            }))
        }
    }
}