package com.sourabh.stockapp.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.sourabh.stockapp.data.remote.dto.IntraDayInfoDto
import com.sourabh.stockapp.domain.module.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun IntraDayInfoDto.toIntraDayInfo(): IntradayInfo {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDataTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayInfo(
        date = localDataTime,
        close = close.toDouble()
    )
}