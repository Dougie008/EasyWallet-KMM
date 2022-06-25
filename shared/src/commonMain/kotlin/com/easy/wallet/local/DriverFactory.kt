package com.easy.wallet.local

import com.easy.wallet.WalletDatabase
import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory() {
    fun createDriver(): SqlDriver
}

internal fun createDatabase(driverFactory: DriverFactory): WalletDatabase {
    val driver = driverFactory.createDriver()
    return WalletDatabase(driver)
}