package com.easy.wallet.exs

fun String.containsHexPrefix(): Boolean {
    return this.length > 1 && this[0] == '0' && this[1] == 'x'
}

fun String.clearHexPrefix(): String {
    return if (this.containsHexPrefix()) {
        this.substring(2)
    } else {
        this
    }
}
