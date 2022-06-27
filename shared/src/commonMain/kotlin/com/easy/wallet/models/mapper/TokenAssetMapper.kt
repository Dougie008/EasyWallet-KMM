package com.easy.wallet.models.mapper

import com.easy.wallet.models.TokenAsset
import com.easy.wallet.models.dto.CoinConfigDto

internal fun CoinConfigDto.toTokenAsset(): TokenAsset {
    return TokenAsset(
        slug = this.slug(),
        symbol = this.coinSymbol,
        icon = this.iconUrl
    )
}