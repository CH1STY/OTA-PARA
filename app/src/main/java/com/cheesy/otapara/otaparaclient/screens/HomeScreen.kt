package com.cheesy.otapara.otaparaclient.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cheesy.otapara.otaparaclient.components.PrimaryButton
import com.cheesy.otapara.otaparaclient.viewmodel.GlobalViewModel
import com.cheesy.otapara.otaparaclient.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    globalViewModel: GlobalViewModel,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by homeViewModel.state.collectAsState()
    val globalState by globalViewModel.state.collectAsState() // You know why I am doing this ( ͡° ͜ʖ ͡°) it's bad practice ? my reply: ( ͡° ͜ʖ ͡°)╭∩╮
    //First Column of this project... I am very excited lmao
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hopefully We will achieve this 🙃")
        Text(text = "Context Package: ${context.opPackageName}")
        Text(text = "state name: ${state.name}")
        Text(text = "global state username ${globalState.username}")
        Text(text = "${navHostController.currentDestination?.route}")
        PrimaryButton() //Just For Fun Will Delete Later
    }


}