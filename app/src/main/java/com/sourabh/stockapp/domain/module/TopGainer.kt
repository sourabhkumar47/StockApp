package com.sourabh.stockapp.domain.module

data class TopGainer(
    val change_amount: String,
    val change_percentage: String,
    val price: String,
    val ticker: String,
    val volume: String
)