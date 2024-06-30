package com.sourabh.stockapp.presentation.stock_list

import com.sourabh.stockapp.domain.module.TopGainer

data class CompanyListingsState(
    val companies: List<TopGainer> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
