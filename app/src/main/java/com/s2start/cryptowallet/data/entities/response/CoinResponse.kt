package com.s2start.cryptowallet.data.entities.response

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("market_cap")
    val marketCap: Long,
    @SerializedName("market_cap_rank")
    val marketCapRank: Long,
    @SerializedName("fully_diluted_valuation")
    val fullyDilutedValuation: Long?,
    @SerializedName("total_volume")
    val totalVolume: Double,
    @SerializedName("high_24h")
    val high24h: Double?,
    @SerializedName("low_24h")
    val low24h: Double?,
    @SerializedName("price_change_24h")
    val priceChange24h: Double?,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double?,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: Double?,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double?,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double,
    @SerializedName("total_supply")
    val totalSupply: Double?,
    @SerializedName("max_supply")
    val maxSupply: Double?,
    val ath: Double,
    @SerializedName("ath_change_percentage")
    val athChangePercentage: Double,
    @SerializedName("ath_date")
    val athDate: String,
    val atl: Double,
    @SerializedName("atl_change_percentage")
    val atlChangePercentage: Double,
    @SerializedName("atl_date")
    val atlDate: String,
    val roi: Roi?,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("sparkline_in_7d")
    val sparklineIn7d: SparklineIn7d,
    @SerializedName("price_change_percentage_24h_in_currency")
    val priceChangePercentage24hInCurrency: Double?,
)

data class Roi(
    val times: Double,
    val currency: String,
    val percentage: Double,
)

data class SparklineIn7d(
    val price: List<Double>,
)
