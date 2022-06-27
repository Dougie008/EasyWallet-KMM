package com.easy.wallet.models

import com.easy.wallet.domain.enums.AssetTag
import com.easy.wallet.domain.enums.NetworkChain
import com.ionspin.kotlin.bignum.integer.BigInteger

data class TokenAsset(
    val slug: String,
    val symbol: String,
    val icon: String,
    val chain: NetworkChain,
    val decimal: Int,
    val contractAddress: String? = null,
    val tag: AssetTag? = null,
    val balance: BigInteger = BigInteger.ZERO
)
