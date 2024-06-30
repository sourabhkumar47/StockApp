package com.sourabh.stockapp.presentation.stock_detail

import com.sourabh.stockapp.domain.module.CompanyInfo
import com.sourabh.stockapp.domain.module.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)