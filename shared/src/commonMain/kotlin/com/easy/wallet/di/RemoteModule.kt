package com.easy.wallet.di

import com.easy.wallet.models.dto.CallParameter
import com.easy.wallet.models.dto.IntListParameter
import com.easy.wallet.models.dto.Parameter
import com.easy.wallet.models.dto.StringParameter
import com.easy.wallet.repositories.AssetsRepository
import com.easy.wallet.serializers.IntListParameterSerializer
import com.easy.wallet.serializers.StringParameterSerializer
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val remoteModule = module {
    singleOf<HttpClient> {
        HttpClient {
            defaultRequest {
                header("Content-type", "application/json")
                contentType(ContentType.Application.Json)
            }

            install(Logging) {
                logger = Logger.DEFAULT
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useArrayPolymorphism = true
                        prettyPrint = true
                        allowStructuredMapKeys = true
                        serializersModule = SerializersModule {
                            polymorphic(Parameter::class) {
                                subclass(CallParameter::class, CallParameter.serializer())
                                subclass(StringParameter::class, StringParameterSerializer)
                                subclass(IntListParameter::class, IntListParameterSerializer)
                            }
                        }
                    }
                )
            }
        }
    }
    singleOf(::AssetsRepository)
}