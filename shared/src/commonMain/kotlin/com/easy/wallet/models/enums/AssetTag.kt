package com.easy.wallet.models.enums

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
enum class AssetTag {
    @SerialName("ERC20")
    ERC20
}