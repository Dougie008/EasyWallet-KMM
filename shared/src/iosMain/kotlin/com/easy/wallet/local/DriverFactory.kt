package com.easy.wallet.local

import com.easy.wallet.WalletDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(WalletDatabase.Schema, "easy_wallet.db")
    }
}