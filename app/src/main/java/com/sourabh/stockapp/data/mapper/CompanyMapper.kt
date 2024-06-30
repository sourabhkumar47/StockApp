package com.sourabh.stockapp.data.mapper

import com.sourabh.stockapp.data.local.TopGainerEntity
import com.sourabh.stockapp.data.remote.dto.CompanyInfoDto
import com.sourabh.stockapp.domain.module.CompanyInfo
import com.sourabh.stockapp.domain.module.TopGainer

fun TopGainerEntity.toGainerList(): TopGainer {
    return TopGainer(
        change_amount = change_amount,
        change_percentage = change_percentage,
        price = price,
        ticker = ticker,
        volume = volume
    )
}

fun TopGainer.toGainerListEntity(): TopGainerEntity {
    return TopGainerEntity(
        change_amount = change_amount,
        change_percentage = change_percentage,
        price = price,
        ticker = ticker,
        volume = volume
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        name = name ?: "",
        symbol = symbol ?: "",
        description = description ?: "",
        industry = industry ?: "",
        country = country ?: "",
        sector = sector ?: "",
        weekLow = weekLow ?: "",
        weekHigh = weekHigh ?: "",
        beta = beta ?: "",
        marketCapitalization = marketCapitalization ?: "",
        peRatio = peRatio ?: "",
        dividendYield = dividendYield ?: "",
        profitMargin = profitMargin ?: "",
        assetType = assetType ?: ""
    )
}
