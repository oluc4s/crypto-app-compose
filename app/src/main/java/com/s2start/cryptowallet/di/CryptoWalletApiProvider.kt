package com.s2start.cryptowallet.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.s2start.cryptowallet.data.remote.MarketsRemoteDataSource
import com.s2start.cryptowallet.data.remote.MarketsService
import com.s2start.cryptowallet.data.usecase.MarketsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CryptoWalletApiProvider {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/v3/coins/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideMarketsService(retrofit: Retrofit): MarketsService = retrofit.create(MarketsService::class.java)

    @Singleton
    @Provides
    fun provideMarketsRemoteDataSource(marketsService: MarketsService) = MarketsRemoteDataSource(marketsService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: MarketsRemoteDataSource) = MarketsUseCase(remoteDataSource)
}