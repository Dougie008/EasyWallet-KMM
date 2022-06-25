package com.easy.wallet.di

import com.easy.wallet.WalletDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.module

actual val platformModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            WalletDatabase.Schema,
            get(),
            "easy_wallet.db"
        )
    }
}