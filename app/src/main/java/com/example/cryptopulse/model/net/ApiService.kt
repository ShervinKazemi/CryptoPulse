package com.example.cryptopulse.model.net

import com.example.cryptopulse.model.data.CoinsData
import com.example.cryptopulse.model.data.TrendingData
import com.example.cryptopulse.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("search/trending")
    suspend fun getTrendingCoins(): Response<TrendingData>

    @GET("coins/markets")
    suspend fun getCoinsList(
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("page") page: Int? = null,
        @Query("per_page") perPage: Int? = null
    ): Response<CoinsData>

    companion object {
        private val retrofit: Retrofit by lazy {
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("X-API-KEY", Constants.API_KEY)
                        .build()
                    chain.proceed(request)
                }
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun create(): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
}