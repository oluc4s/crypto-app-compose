package com.s2start.cryptowallet.data.usecase

import com.s2start.cryptowallet.data.remote.MarketsRemoteDataSource
import javax.inject.Inject

class MarketsUseCase @Inject constructor(private val remoteDataSource: MarketsRemoteDataSource){
    suspend fun getListCoin() = remoteDataSource.getListCoin()
}