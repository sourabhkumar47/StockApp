package com.sourabh.stockapp.domain.module

import com.squareup.moshi.Json

data class CompanyInfo(
    val symbol: String?,
    val assetType: String?,
    val description: String?,
    val name: String?,
    val country: String?,
    val industry: String?,
    val sector: String?,
    val weekLow: String?,
    val weekHigh: String?,
    val beta: String?,
    val marketCapitalization: String?,
    val peRatio: String?,
    val dividendYield: String?,
    val profitMargin: String?,
)
