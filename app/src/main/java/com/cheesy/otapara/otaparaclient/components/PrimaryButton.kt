package com.cheesy.otapara.otaparaclient.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, onPrimaryButtonClick: () -> Unit) {
    Button(modifier = modifier, onClick = onPrimaryButtonClick) {
        Text(text = "CONNECT TO MAL")
    }
}