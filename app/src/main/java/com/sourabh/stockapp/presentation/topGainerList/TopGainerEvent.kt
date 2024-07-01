package com.sourabh.stockapp.presentation.topGainerList

sealed class TopGainerEvent {
//    object Refresh: TopGainerEvent()
    data class OnSearchQueryChange(val query: String): TopGainerEvent()
}
