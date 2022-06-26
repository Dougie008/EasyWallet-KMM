package com.easy.wallet.di

import org.koin.core.module.Module

fun appModule(initModule: Module? = null) = initModule?.let {
    listOf(it, remoteModule, platformModule)
} ?: listOf(remoteModule, platformModule)