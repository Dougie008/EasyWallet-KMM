package com.easy.wallet.models.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
internal data class BaseResponseDto<T>(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: T,
    @SerialName("error")
    val error: String? = null
)