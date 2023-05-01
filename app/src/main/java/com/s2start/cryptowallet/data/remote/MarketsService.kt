package com.s2start.cryptowallet.data.remote

import com.s2start.cryptowallet.data.entities.response.CoinResponse
import retrofit2.Response
import retrofit2.http.GET

interface MarketsService {
    @GET("markets?vs_currency=usd&order=market_cap_desc&per_page=250&page=1&sparkline=true&price_change_percentage=24h")
    suspend fun getListCoin(): Response<List<CoinResponse>>
}