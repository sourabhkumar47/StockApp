package com.sourabh.stockapp.nav_utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sourabh.stockapp.presentation.stock_detail.CompanyInfoScreen
import com.sourabh.stockapp.presentation.topGainerList.TopGainerScreen
import com.sourabh.stockapp.presentation.topLoserList.TopLoserScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController, startDestination = Screen.TopLoserScreen.route) {
        composable(Screen.TopGainerScreen.route) {
            TopGainerScreen(navController = navController)
        }

        composable(Screen.TopLoserScreen.route) {
            TopLoserScreen(navController = navController)
        }

        composable(
            route = "${Screen.Details.route}/{symbol}",
            arguments = listOf(
                navArgument("symbol") {
                    type = NavType.StringType
                },
            )
        ) { backStackEntry ->
            val symbol = backStackEntry.arguments?.getString("symbol")
            if (symbol != null) {
                CompanyInfoScreen(symbol = symbol)
            }
        }
    }
}

@Composable
fun NavigationScreens(navController: NavHostController) {
    NavHost(navController, startDestination = NavItem.Gainer.path) {
        composable(NavItem.Gainer.path) { TopGainerScreen(navController = navController) }
        composable(NavItem.Loser.path) { TopLoserScreen(navController = navController) }
    }
}