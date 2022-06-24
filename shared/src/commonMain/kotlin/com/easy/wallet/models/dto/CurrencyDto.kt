package com.easy.wallet.models.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
internal data class CurrencyDto(
    @SerialName("chain")
    val chain: String,
    @SerialName("chain_name")
    val chainName: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("colorful_image_url")
    val imageUrl: String,
)