package com.easy.wallet.models.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
internal data class BaseResponseDto(
    @SerialName("code")
    val code: Int,
    @SerialName("currencies")
    val currencies: List<CurrencyDto>,
    @SerialName("ok")
    val ok: Boolean,
    @SerialName("total")
    val total: Int
)