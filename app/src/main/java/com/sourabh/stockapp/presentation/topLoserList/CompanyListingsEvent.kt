package com.sourabh.stockapp.presentation.topLoserList

import com.sourabh.stockapp.presentation.topGainerList.CompanyListingsEvent

sealed class TopLoserEvent {
    object Refresh: TopLoserEvent()
    data class OnSearchQueryChange(val query: String): TopLoserEvent()
}
