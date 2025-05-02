package com.example.cryptopulse.model.repositories.home

import com.example.cryptopulse.model.data.CoinsData
import com.example.cryptopulse.model.data.TrendingData
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getTrendingCoins(): Flow<TrendingData>

    suspend fun getCoinsList(): Flow<CoinsData>
}