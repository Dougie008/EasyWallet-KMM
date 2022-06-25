package com.easy.wallet.remote

import com.easy.wallet.models.TokenAsset
import com.easy.wallet.models.dto.BaseResponseDto
import com.easy.wallet.models.mapper.toTokenAsset
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class EasyApi(
    private val httpClient: HttpClient
) {
    suspend fun loadAssets(): List<TokenAsset> {
        val response = httpClient.get("") {
            parameter("page_size", 20)

        }.body<BaseResponseDto>()
        return response.currencies.map {
            it.toTokenAsset()
        }
    }
}