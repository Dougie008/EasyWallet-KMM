package com.easy.wallet.models.mapper

import com.easy.wallet.models.TokenAsset
import com.easy.wallet.models.dto.CoinConfigDto
import com.ionspin.kotlin.bignum.integer.BigInteger

internal fun CoinConfigDto.toTokenAsset(
    balance: BigInteger = BigInteger.ZERO
): TokenAsset {
    return TokenAsset(
        slug = this.slug(),
        chain = this.chain,
        symbol = this.coinSymbol,
        decimal = this.decimal,
        contractAddress = this.contractAddress,
        icon = this.iconUrl,
        tag = this.tag,
        balance = balance
    )
}