package com.sourabh.stockapp.nav_utils

sealed class Screen(val route:String) {
    data object TopGainerScreen : Screen("gainer_screen")
    data object TopLoserScreen : Screen("loser_screen")
    data object Details : Screen("details")
}