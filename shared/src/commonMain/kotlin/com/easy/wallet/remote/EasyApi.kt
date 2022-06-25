package com.easy.wallet.remote

import com.easy.wallet.WalletDatabase
import com.easy.wallet.models.TokenAsset
import com.easy.wallet.models.dto.BaseResponseDto
import com.easy.wallet.models.mapper.toTokenAsset
import com.squareup.sqldelight.runtime.coroutines.asFlow
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EasyApi(
    private val httpClient: HttpClient,
    private val database: WalletDatabase
) {
    suspend fun loadAssets(): List<TokenAsset> {
        val response = try {
            httpClient.get("") {
                parameter("page_size", 20)
            }.body<BaseResponseDto>().currencies.map {
                it.toTokenAsset()
            }
        }catch (e: Exception) {
            emptyList()
        }
        return response
    }

    fun insertToken() {
        database.assetsQueries.transaction {
            database.assetsQueries.insertTokenAsset(com.easy.wallet.TokenAssetEntity(
                slug = "hello",
                symbol = "world",
                icon = "icon_url",
                decimal = 8
            ))
        }
    }

    fun loadLocalTokenAsset(): String {
        return database.assetsQueries.selectAll().executeAsList().toString()
    }
}