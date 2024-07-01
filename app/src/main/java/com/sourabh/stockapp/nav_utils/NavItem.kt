package com.sourabh.stockapp.nav_utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp

sealed class NavItem {
    object Gainer :
        Item(
            path = NavPath.GAINER.toString(),
            title = NavTitle.GAINER,
            icon = Icons.Default.ArrowDropUp
        )

    object Loser :
        Item(
            path = NavPath.LOSER.toString(),
            title = NavTitle.LOSER,
            icon = Icons.Default.ArrowDropDown
        )

}