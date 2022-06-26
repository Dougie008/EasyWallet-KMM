package com.easy.wallet.repositories

import com.easy.wallet.WalletDatabase
import com.easy.wallet.models.TokenAsset
import com.easy.wallet.models.dto.BaseResponseDto
import com.easy.wallet.models.mapper.toTokenAsset
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AssetsRepository(
    private val httpClient: HttpClient,
    sqlDriver: SqlDriver
) {
    private val database = WalletDatabase(sqlDriver)

    suspend fun loadAssets(): List<TokenAsset> {
        val response = try {
            httpClient.get("") {
                parameter("page_size", 20)
            }.body<BaseResponseDto>().currencies.map {
                it.toTokenAsset()
            }
        } catch (e: Exception) {
            emptyList()
        }
        return response
    }

    fun insertToken() {
        database.assetsQueries.transaction {
            database.assetsQueries.insertTokenAsset(
                com.easy.wallet.TokenAssetEntity(
                    slug = "hello2",
                    symbol = "world2",
                    icon = "icon_url2",
                    decimal = 8
                )
            )
        }
    }

    fun loadLocalTokenAsset(): Flow<String> {
        return database.assetsQueries.selectAll().asFlow().map {
            it.executeAsList().toString()
        }
    }
}