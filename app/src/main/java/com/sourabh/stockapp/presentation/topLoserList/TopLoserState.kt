package com.sourabh.stockapp.presentation.topLoserList

import com.sourabh.stockapp.domain.module.TopLoser

data class TopLoserState(
    val companies: List<TopLoser> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
