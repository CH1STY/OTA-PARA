package com.cheesy.otapara.otaparaclient.navigation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cheesy.otapara.otaparaclient.screens.LoginScreen
import com.cheesy.otapara.otaparaclient.viewmodel.GlobalViewModel

@Composable
fun Navigation(navController: NavController, globalViewModel: GlobalViewModel = hiltViewModel()) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.Login.route
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navHostController = navController, globalViewModel)
        }
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navHostController = navController, globalViewModel = globalViewModel)
        }
    }
}