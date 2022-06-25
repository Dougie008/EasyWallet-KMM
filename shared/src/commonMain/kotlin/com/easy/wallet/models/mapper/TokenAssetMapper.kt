package com.easy.wallet.models.mapper

import com.easy.wallet.models.TokenAsset
import com.easy.wallet.models.dto.CurrencyDto

internal fun CurrencyDto.toTokenAsset(): TokenAsset {
    return TokenAsset(
        slug = this.slug,
        symbol = this.symbol,
        icon = this.imageUrl
    )
}