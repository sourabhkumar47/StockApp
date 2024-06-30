package com.sourabh.stockapp.domain.repository

import com.sourabh.stockapp.domain.module.TopGainer
import com.sourabh.stockapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getTopGainerList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<TopGainer>>>
}