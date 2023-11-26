package com.cheesy.otapara.otaparaclient.screens

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cheesy.otapara.R
import com.cheesy.otapara.otaparaclient.components.PrimaryButton
import com.cheesy.otapara.otaparaclient.navigation.Screen
import com.cheesy.otapara.otaparaclient.util.thirdOfScreenHeight
import com.cheesy.otapara.otaparaclient.viewmodel.GlobalViewModel
import com.cheesy.otapara.otaparaclient.viewmodel.LoginViewModel


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LoginScreen(
    navHostController: NavHostController,
    globalViewModel: GlobalViewModel,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by loginViewModel.state.collectAsState()
    val globalState by globalViewModel.state.collectAsState() // You know why I am doing this ( ͡° ͜ʖ ͡°) it's bad practice ? my reply: ( ͡° ͜ʖ ͡°)╭∩╮
    val customWebViewClient = getCustomWebViewClient(context, loginViewModel, globalViewModel)
    var currentWebView: WebView? = null
    if (globalState.userMalToken.isNotEmpty()) {
        navHostController.navigate(Screen.Home.route)
    } else if (state.isLoadingToken) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoadingTimePassed) {
                Button(onClick = { loginViewModel.tryAgain() }) {
                    Text(text = "Try Again")
                }
            } else {
                CircularProgressIndicator()
            }
        }
    } else {
        if (state.isLoginOnGoing && state.loginUrl.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                AndroidView(factory = {
                    WebView(it).apply {
                        settings.javaScriptEnabled = true
                        currentWebView = this
                        webViewClient = customWebViewClient
                        loadUrl(state.loginUrl)
                    }

                })
                Button(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopEnd),
                    onClick = { currentWebView?.loadUrl(state.loginUrl) }) {
                    Icon(Icons.Default.Refresh, "Refresh")
                }
            }
        } else {
            //First Column of this project... I am very excited lmao
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
//                Text(text = "Hopefully We will achieve this 🙃")
//                Text(text = "Context Package: ${context.opPackageName}")
//                Text(text = "state name: ${state.name}")
//                Text(text = "global state username ${globalState.username}")
//                Text(text = "${navHostController.currentDestination?.route}")
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.home_background),
                    contentDescription = "HomeBackgroundImage",
                    colorFilter = ColorFilter.tint(Color(0xA4000000), blendMode = BlendMode.SrcOver),
                    contentScale = ContentScale.Crop
                )
                PrimaryButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = thirdOfScreenHeight(context)),
                    onPrimaryButtonClick = {
                        loginViewModel.loginThroughApi(context)
                    }) //Just For Fun Will Delete Later

            }
        }
    }


}

fun getCustomWebViewClient(
    context: Context,
    loginViewModel: LoginViewModel,
    globalViewModel: GlobalViewModel
): WebViewClient = object : WebViewClient() {
    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        if (request != null) {
            //If Code is Achieved from the url response get token
            if (request.url.toString().contains("otapara-app")) {
                loginViewModel.fetchTokenBasedOnCode(
                    context = context,
                    code = request.url.getQueryParameter("code"),
                    globalViewModel = globalViewModel
                )
            }
        }
        return super.shouldOverrideUrlLoading(view, request)
    }
}

