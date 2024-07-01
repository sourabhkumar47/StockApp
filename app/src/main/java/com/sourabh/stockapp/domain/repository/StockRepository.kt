package com.sourabh.stockapp.domain.repository

import com.sourabh.stockapp.domain.module.CompanyInfo
import com.sourabh.stockapp.domain.module.IntradayInfo
import com.sourabh.stockapp.domain.module.TopGainer
import com.sourabh.stockapp.domain.module.TopLoser
import com.sourabh.stockapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getTopGainerList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<TopGainer>>>

    suspend fun getTopLoserList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<TopLoser>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>
}