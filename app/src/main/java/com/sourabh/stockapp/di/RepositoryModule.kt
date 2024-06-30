package com.sourabh.stockapp.di

import com.sourabh.stockapp.data.csv.CSVParser
import com.sourabh.stockapp.data.csv.IntradayInfoParser
import com.sourabh.stockapp.data.repository.StockRepositoryImpl
import com.sourabh.stockapp.domain.module.IntradayInfo
import com.sourabh.stockapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}