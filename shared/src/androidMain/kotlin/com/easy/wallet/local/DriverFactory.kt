package com.easy.wallet.local

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        TODO()
    }
}