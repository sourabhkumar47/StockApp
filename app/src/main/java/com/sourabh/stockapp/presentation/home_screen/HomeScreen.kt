package com.sourabh.stockapp.presentation.home_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ramcosta.composedestinations.utils.composable
import com.sourabh.stockapp.nav_utils.NavItem
import com.sourabh.stockapp.nav_utils.Navigation
import com.sourabh.stockapp.nav_utils.NavigationScreens
import com.sourabh.stockapp.nav_utils.Screen
import com.sourabh.stockapp.nav_utils.tabs.BottomNavigationBar

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(bottomBar = {
        BottomAppBar { BottomNavigationBar(navController = navController) }
    }) {
//        Navigation(navController = navController)
        NavigationScreens(navController = navController)
    }
}