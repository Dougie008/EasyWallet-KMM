package com.easy.wallet.domain.models

import com.easy.wallet.domain.enums.NetworkChain

data class AssetAccount(
    val chain: NetworkChain,
    val account: Int,
    val change: Int,
    val addressIndex: Int
)
