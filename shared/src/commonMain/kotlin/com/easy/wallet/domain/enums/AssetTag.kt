package com.easy.wallet.domain.enums

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
enum class AssetTag {
    @SerialName("ERC20")
    ERC20
}