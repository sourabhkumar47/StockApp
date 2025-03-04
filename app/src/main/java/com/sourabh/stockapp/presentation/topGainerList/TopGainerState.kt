package com.sourabh.stockapp.presentation.topGainerList

import com.sourabh.stockapp.domain.module.TopGainer

data class TopGainerState(
    val companies: List<TopGainer> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
