package com.example.cryptopulse.model.net

import com.example.cryptopulse.model.data.TrendingData
import com.example.cryptopulse.util.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(Constants.API_KEY)
    @GET("search/trending")
    suspend fun getTrendingCoins() :Response<TrendingData>


}

fun createApiService() :ApiService {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}