package com.s2start.cryptowallet.data.remote

import com.s2start.cryptowallet.util.BaseDataSource
import com.s2start.cryptowallet.util.Resource
import retrofit2.Response
import javax.inject.Inject

class MarketsRemoteDataSource  @Inject constructor(
    private val marketsService: MarketsService
): BaseDataSource() {

    suspend fun getListCoin() = getResult { marketsService.getListCoin() }
}
