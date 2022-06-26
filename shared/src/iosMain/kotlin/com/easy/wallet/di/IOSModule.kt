package com.easy.wallet.di

import com.easy.wallet.WalletDatabase
import com.easy.wallet.viewmodels.AssetsCallbackViewModel
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    single<SqlDriver> {
        NativeSqliteDriver(WalletDatabase.Schema, "easy_wallet.db")
    }
    singleOf(::AssetsCallbackViewModel)
}