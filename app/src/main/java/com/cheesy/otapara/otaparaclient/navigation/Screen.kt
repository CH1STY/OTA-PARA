package com.cheesy.otapara.otaparaclient.navigation

sealed class Screen(val route:String) {
    object Home : Screen("Home_Screen")
}