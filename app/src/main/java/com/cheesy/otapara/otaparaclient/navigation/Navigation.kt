package com.cheesy.otapara.otaparaclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cheesy.otapara.otaparaclient.screens.HomeScreen
import com.cheesy.otapara.otaparaclient.viewmodel.GlobalViewModel

@Composable
fun Navigation(navController: NavController,globalViewModel: GlobalViewModel = hiltViewModel()) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navHostController = navController,globalViewModel)
        }
    }
}