package com.sourabh.stockapp.nav_utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.TrendingUp

sealed class NavItem {
    object Gainer :
        Item(
            path = NavPath.GAINER.toString(),
            title = NavTitle.GAINER,
            icon = Icons.AutoMirrored.Filled.TrendingUp
        )

    object Loser :
        Item(
            path = NavPath.LOSER.toString(),
            title = NavTitle.LOSER,
            icon = Icons.AutoMirrored.Filled.TrendingDown
        )

}