package com.example.cryptopulse.model.repositories.home

import com.example.cryptopulse.model.data.TrendingData
import com.example.cryptopulse.model.net.ApiService

class HomeRepositoryImpl(private val apiService: ApiService) :HomeRepository {
    override suspend fun getTrendingCoins(): TrendingData {
        return apiService.getTrendingCoins()
    }
}