package com.easy.wallet

import com.easy.wallet.models.TokenAsset
import com.easy.wallet.models.dto.BaseResponseDto
import com.easy.wallet.models.mapper.toTokenAsset
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Greeting() {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getHtml(): List<TokenAsset> {
        val response = client.get("") {
            parameter("page_size", 20)

        }.body<BaseResponseDto>()
        return response.currencies.map {
            it.toTokenAsset()
        }
    }
}