package com.example.cryptopulse.model.repositories.home

import com.example.cryptopulse.model.data.TrendingData

interface HomeRepository {

    suspend fun getTrendingCoins() :TrendingData

}