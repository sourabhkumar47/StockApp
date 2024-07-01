package com.sourabh.stockapp.presentation.topLoserList

sealed class TopLoserEvent {
//    object Refresh: TopLoserEvent()
    data class OnSearchQueryChange(val query: String): TopLoserEvent()
}
