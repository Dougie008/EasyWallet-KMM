package com.easy.wallet.di

import com.easy.wallet.Greeting
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val remoteModule = module {
    singleOf(::Greeting)
}