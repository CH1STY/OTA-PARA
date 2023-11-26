package com.cheesy.otapara.otaparaclient.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun thirdOfScreenHeight(context: Context): Dp {
    val displayMetrics = context?.resources?.displayMetrics
    val heightPixels = displayMetrics?.heightPixels ?: 0
    val density = displayMetrics?.density ?: 1f
    val heightDP = heightPixels / density

    return (heightDP/3).dp

}
fun getWindowHeight(windowManager: WindowManager): Float {
    //For Android 11+ New Code
    return windowManager.currentWindowMetrics.bounds.height().toFloat()
}