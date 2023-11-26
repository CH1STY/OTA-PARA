package com.cheesy.otapara.otaparaclient.navigation

sealed class Screen(val route:String) {
    object Login : Screen("Login_Screen")
    object Home : Screen("Home_Screen")
}