package com.example.cryptopulse.model.repositories.home

import com.example.cryptopulse.model.data.CoinsData
import com.example.cryptopulse.model.data.TrendingData
import com.example.cryptopulse.model.net.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException


class HomeRepositoryImpl(private val apiService: ApiService) : HomeRepository {

    override suspend fun getTrendingCoins(): Flow<TrendingData> {
        return flow {
            emit(handleResponse { apiService.getTrendingCoins() })
        }
    }

    override suspend fun getCoinsList(): Flow<CoinsData> {
        return flow {
            emit(handleResponse { apiService.getCoinsList() })
        }
    }

    private inline fun <T> handleResponse(call: () -> Response<T>): T {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IOException("Empty response body")
            return body
        } else {
            throw IOException("API error: ${response.code()} - ${response.message()}")
        }
    }
}