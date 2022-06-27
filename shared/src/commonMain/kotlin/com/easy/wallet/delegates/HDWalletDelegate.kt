package com.easy.wallet.delegates

import com.easy.wallet.domain.enums.NetworkChain

class HDWalletDelegate() {
    fun address(chain: NetworkChain): String {
        return ""
    }
    fun sign(): String {
        return ""
    }
}