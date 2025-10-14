package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@Composable
fun LoadingShell() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { CircularProgressIndicator() }
}

@Composable
fun ErrorShell(
    message: String,
    onRetry: () -> Unit
) {
    ApplicationConfig.config.errorScreen(message) { onRetry() }
}