package com.easy.wallet.android

import android.app.Application

class EasyApplication: Application() {
    init {
        System.loadLibrary("TrustWalletCore")
    }
}