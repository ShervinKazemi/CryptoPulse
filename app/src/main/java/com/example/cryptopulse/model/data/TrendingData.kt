package com.example.cryptopulse.model.data


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.JsonElement

@Parcelize
data class TrendingData(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("coins")
    val coins: List<Coin>,
    @SerializedName("nfts")
    val nfts: List<Nft>
) : Parcelable {
    @Parcelize
    data class Category(
        @SerializedName("coins_count")
        val coinsCount: String,
        @SerializedName("data")
        val `data`: Data,
        @SerializedName("id")
        val id: Int,
        @SerializedName("market_cap_1h_change")
        val marketCap1hChange: Double,
        @SerializedName("name")
        val name: String,
        @SerializedName("slug")
        val slug: String
    ) : Parcelable {
        @Parcelize
        data class Data(
            @SerializedName("market_cap")
            val marketCap: Double,
            @SerializedName("market_cap_btc")
            val marketCapBtc: Double,
            @SerializedName("market_cap_change_percentage_24h")
            val marketCapChangePercentage24h: MarketCapChangePercentage24h,
            @SerializedName("sparkline")
            val sparkline: String,
            @SerializedName("total_volume")
            val totalVolume: Double,
            @SerializedName("total_volume_btc")
            val totalVolumeBtc: Double
        ) : Parcelable {
            @Parcelize
            data class MarketCapChangePercentage24h(
                @SerializedName("aed")
                val aed: Double,
                @SerializedName("ars")
                val ars: Double,
                @SerializedName("aud")
                val aud: Double,
                @SerializedName("bch")
                val bch: Double,
                @SerializedName("bdt")
                val bdt: Double,
                @SerializedName("bhd")
                val bhd: Double,
                @SerializedName("bits")
                val bits: Double,
                @SerializedName("bmd")
                val bmd: Double,
                @SerializedName("bnb")
                val bnb: Double,
                @SerializedName("brl")
                val brl: Double,
                @SerializedName("btc")
                val btc: Double,
                @SerializedName("cad")
                val cad: Double,
                @SerializedName("chf")
                val chf: Double,
                @SerializedName("clp")
                val clp: Double,
                @SerializedName("cny")
                val cny: Double,
                @SerializedName("czk")
                val czk: Double,
                @SerializedName("dkk")
                val dkk: Double,
                @SerializedName("dot")
                val dot: Double,
                @SerializedName("eos")
                val eos: Double,
                @SerializedName("eth")
                val eth: Double,
                @SerializedName("eur")
                val eur: Double,
                @SerializedName("gbp")
                val gbp: Double,
                @SerializedName("gel")
                val gel: Double,
                @SerializedName("hkd")
                val hkd: Double,
                @SerializedName("huf")
                val huf: Double,
                @SerializedName("idr")
                val idr: Double,
                @SerializedName("ils")
                val ils: Double,
                @SerializedName("inr")
                val inr: Double,
                @SerializedName("jpy")
                val jpy: Double,
                @SerializedName("krw")
                val krw: Double,
                @SerializedName("kwd")
                val kwd: Double,
                @SerializedName("link")
                val link: Double,
                @SerializedName("lkr")
                val lkr: Double,
                @SerializedName("ltc")
                val ltc: Double,
                @SerializedName("mmk")
                val mmk: Double,
                @SerializedName("mxn")
                val mxn: Double,
                @SerializedName("myr")
                val myr: Double,
                @SerializedName("ngn")
                val ngn: Double,
                @SerializedName("nok")
                val nok: Double,
                @SerializedName("nzd")
                val nzd: Double,
                @SerializedName("php")
                val php: Double,
                @SerializedName("pkr")
                val pkr: Double,
                @SerializedName("pln")
                val pln: Double,
                @SerializedName("rub")
                val rub: Double,
                @SerializedName("sar")
                val sar: Double,
                @SerializedName("sats")
                val sats: Double,
                @SerializedName("sek")
                val sek: Double,
                @SerializedName("sgd")
                val sgd: Double,
                @SerializedName("thb")
                val thb: Double,
                @SerializedName("try")
                val tryX: Double,
                @SerializedName("twd")
                val twd: Double,
                @SerializedName("uah")
                val uah: Double,
                @SerializedName("usd")
                val usd: Double,
                @SerializedName("vef")
                val vef: Double,
                @SerializedName("vnd")
                val vnd: Double,
                @SerializedName("xag")
                val xag: Double,
                @SerializedName("xau")
                val xau: Double,
                @SerializedName("xdr")
                val xdr: Double,
                @SerializedName("xlm")
                val xlm: Double,
                @SerializedName("xrp")
                val xrp: Double,
                @SerializedName("yfi")
                val yfi: Double,
                @SerializedName("zar")
                val zar: Double
            ) : Parcelable
        }
    }

    @Parcelize
    data class Coin(
        @SerializedName("item")
        val item: Item
    ) : Parcelable {
        @Parcelize
        data class Item(
            @SerializedName("coin_id")
            val coinId: Int,
            @SerializedName("data")
            val `data`: Data,
            @SerializedName("id")
            val id: String,
            @SerializedName("large")
            val large: String,
            @SerializedName("market_cap_rank")
            val marketCapRank: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("price_btc")
            val priceBtc: Double,
            @SerializedName("score")
            val score: Int,
            @SerializedName("slug")
            val slug: String,
            @SerializedName("small")
            val small: String,
            @SerializedName("symbol")
            val symbol: String,
            @SerializedName("thumb")
            val thumb: String
        ) : Parcelable {
            @Parcelize
            data class Data(
                @SerializedName("content")
                val content: Content,
                @SerializedName("market_cap")
                val marketCap: String,
                @SerializedName("market_cap_btc")
                val marketCapBtc: String,
                @SerializedName("price")
                val price: Double,
                @SerializedName("price_btc")
                val priceBtc: String,
                @SerializedName("price_change_percentage_24h")
                val priceChangePercentage24h: PriceChangePercentage24h,
                @SerializedName("sparkline")
                val sparkline: String,
                @SerializedName("total_volume")
                val totalVolume: String,
                @SerializedName("total_volume_btc")
                val totalVolumeBtc: String
            ) : Parcelable {
                @Parcelize
                data class Content(
                    @SerializedName("description")
                    val description: String,
                    @SerializedName("title")
                    val title: String
                ) : Parcelable

                @Parcelize
                data class PriceChangePercentage24h(
                    @SerializedName("aed")
                    val aed: Double,
                    @SerializedName("ars")
                    val ars: Double,
                    @SerializedName("aud")
                    val aud: Double,
                    @SerializedName("bch")
                    val bch: Double,
                    @SerializedName("bdt")
                    val bdt: Double,
                    @SerializedName("bhd")
                    val bhd: Double,
                    @SerializedName("bits")
                    val bits: Double,
                    @SerializedName("bmd")
                    val bmd: Double,
                    @SerializedName("bnb")
                    val bnb: Double,
                    @SerializedName("brl")
                    val brl: Double,
                    @SerializedName("btc")
                    val btc: Double,
                    @SerializedName("cad")
                    val cad: Double,
                    @SerializedName("chf")
                    val chf: Double,
                    @SerializedName("clp")
                    val clp: Double,
                    @SerializedName("cny")
                    val cny: Double,
                    @SerializedName("czk")
                    val czk: Double,
                    @SerializedName("dkk")
                    val dkk: Double,
                    @SerializedName("dot")
                    val dot: Double,
                    @SerializedName("eos")
                    val eos: Double,
                    @SerializedName("eth")
                    val eth: Double,
                    @SerializedName("eur")
                    val eur: Double,
                    @SerializedName("gbp")
                    val gbp: Double,
                    @SerializedName("gel")
                    val gel: Double,
                    @SerializedName("hkd")
                    val hkd: Double,
                    @SerializedName("huf")
                    val huf: Double,
                    @SerializedName("idr")
                    val idr: Double,
                    @SerializedName("ils")
                    val ils: Double,
                    @SerializedName("inr")
                    val inr: Double,
                    @SerializedName("jpy")
                    val jpy: Double,
                    @SerializedName("krw")
                    val krw: Double,
                    @SerializedName("kwd")
                    val kwd: Double,
                    @SerializedName("link")
                    val link: Double,
                    @SerializedName("lkr")
                    val lkr: Double,
                    @SerializedName("ltc")
                    val ltc: Double,
                    @SerializedName("mmk")
                    val mmk: Double,
                    @SerializedName("mxn")
                    val mxn: Double,
                    @SerializedName("myr")
                    val myr: Double,
                    @SerializedName("ngn")
                    val ngn: Double,
                    @SerializedName("nok")
                    val nok: Double,
                    @SerializedName("nzd")
                    val nzd: Double,
                    @SerializedName("php")
                    val php: Double,
                    @SerializedName("pkr")
                    val pkr: Double,
                    @SerializedName("pln")
                    val pln: Double,
                    @SerializedName("rub")
                    val rub: Double,
                    @SerializedName("sar")
                    val sar: Double,
                    @SerializedName("sats")
                    val sats: Double,
                    @SerializedName("sek")
                    val sek: Double,
                    @SerializedName("sgd")
                    val sgd: Double,
                    @SerializedName("sol")
                    val sol: Double,
                    @SerializedName("thb")
                    val thb: Double,
                    @SerializedName("try")
                    val tryX: Double,
                    @SerializedName("twd")
                    val twd: Double,
                    @SerializedName("uah")
                    val uah: Double,
                    @SerializedName("usd")
                    val usd: Double,
                    @SerializedName("vef")
                    val vef: Double,
                    @SerializedName("vnd")
                    val vnd: Double,
                    @SerializedName("xag")
                    val xag: Double,
                    @SerializedName("xau")
                    val xau: Double,
                    @SerializedName("xdr")
                    val xdr: Double,
                    @SerializedName("xlm")
                    val xlm: Double,
                    @SerializedName("xrp")
                    val xrp: Double,
                    @SerializedName("yfi")
                    val yfi: Double,
                    @SerializedName("zar")
                    val zar: Double
                ) : Parcelable
            }
        }
    }

    @Parcelize
    data class Nft(
        @SerializedName("data")
        val `data`: Data,
        @SerializedName("floor_price_24h_percentage_change")
        val floorPrice24hPercentageChange: Double,
        @SerializedName("floor_price_in_native_currency")
        val floorPriceInNativeCurrency: Double,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("native_currency_symbol")
        val nativeCurrencySymbol: String,
        @SerializedName("nft_contract_id")
        val nftContractId: Int,
        @SerializedName("symbol")
        val symbol: String,
        @SerializedName("thumb")
        val thumb: String
    ) : Parcelable {
        @Parcelize
        data class Data(
            @SerializedName("content")
            val content: String? = null,
            @SerializedName("floor_price")
            val floorPrice: String,
            @SerializedName("floor_price_in_usd_24h_percentage_change")
            val floorPriceInUsd24hPercentageChange: String,
            @SerializedName("h24_average_sale_price")
            val h24AverageSalePrice: String,
            @SerializedName("h24_volume")
            val h24Volume: String,
            @SerializedName("sparkline")
            val sparkline: String
        ) : Parcelable
    }
}