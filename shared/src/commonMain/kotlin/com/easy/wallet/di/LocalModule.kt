package com.easy.wallet.di

import com.easy.wallet.local.DriverFactory
import com.easy.wallet.local.createDatabase
import org.koin.dsl.module

val localModule = module {
    single {
        createDatabase(DriverFactory())
    }
}
