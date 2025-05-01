package com.example.cryptopulse.model.repositories.home

import com.example.cryptopulse.model.data.TrendingData
import com.example.cryptopulse.model.net.ApiService
import java.io.IOException

class HomeRepositoryImpl(private val apiService: ApiService) : HomeRepository {
    override suspend fun getTrendingCoins(): TrendingData {
        val response = apiService.getTrendingCoins()
        if (response.isSuccessful) {
            val trendingResponse = response.body()
            if (trendingResponse != null) {
                return trendingResponse
            } else {
                throw IOException("Empty data body")
            }
        } else {
            throw IOException("Api error : " + response.code().toString())
        }
    }
}