package com.example.cryptopulse.model.data

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
class CoinsData : ArrayList<CoinsData.CoinsDataItem>(), Parcelable {
    @Parcelize
    data class CoinsDataItem(
        @SerializedName("ath")
        val ath: Double,
        @SerializedName("ath_change_percentage")
        val athChangePercentage: Double,
        @SerializedName("ath_date")
        val athDate: String,
        @SerializedName("atl")
        val atl: Double,
        @SerializedName("atl_change_percentage")
        val atlChangePercentage: Double,
        @SerializedName("atl_date")
        val atlDate: String,
        @SerializedName("circulating_supply")
        val circulatingSupply: Double,
        @SerializedName("current_price")
        val currentPrice: Double,
        @SerializedName("fully_diluted_valuation")
        val fullyDilutedValuation: Long,
        @SerializedName("high_24h")
        val high24h: Double,
        @SerializedName("id")
        val id: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("last_updated")
        val lastUpdated: String,
        @SerializedName("low_24h")
        val low24h: Double,
        @SerializedName("market_cap")
        val marketCap: Long,
        @SerializedName("market_cap_change_24h")
        val marketCapChange24h: Double,
        @SerializedName("market_cap_change_percentage_24h")
        val marketCapChangePercentage24h: Double,
        @SerializedName("market_cap_rank")
        val marketCapRank: Int,
        @SerializedName("max_supply")
        val maxSupply: Double,
        @SerializedName("name")
        val name: String,
        @SerializedName("price_change_24h")
        val priceChange24h: Double,
        @SerializedName("price_change_percentage_24h")
        val priceChangePercentage24h: Double,
        @SerializedName("roi")
        val roi: Roi? = null, // Made nullable
        @SerializedName("symbol")
        val symbol: String,
        @SerializedName("total_supply")
        val totalSupply: Double,
        @SerializedName("total_volume")
        val totalVolume: Double
    ) : Parcelable {
        override fun hashCode(): Int {
            var result = id.hashCode()
            result = 31 * result + name.hashCode()
            result = 31 * result + symbol.hashCode()
            result = 31 * result + currentPrice.hashCode()
            result = 31 * result + (roi?.hashCode() ?: 0) // Safe null handling
            return result
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CoinsDataItem

            if (id != other.id) return false
            if (name != other.name) return false
            if (symbol != other.symbol) return false
            if (currentPrice != other.currentPrice) return false
            if (roi != other.roi) return false

            return true
        }

        @Parcelize
        data class Roi(
            @SerializedName("currency")
            val currency: String,
            @SerializedName("percentage")
            val percentage: Double,
            @SerializedName("times")
            val times: Double
        ) : Parcelable
    }
}