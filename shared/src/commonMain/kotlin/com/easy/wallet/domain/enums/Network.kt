package com.easy.wallet.domain.enums

enum class Network(
    val label: String,
    val id: Int
) {
    MAIN("MainNet", 1), ROPSTEN("Ropsten", 3), RINKEBY("Rinkeby", 4)
}