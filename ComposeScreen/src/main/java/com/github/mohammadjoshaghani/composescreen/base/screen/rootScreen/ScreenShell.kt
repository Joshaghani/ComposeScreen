package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@Composable
fun LoadingShell() {
    androidx.compose.foundation.layout.Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) { androidx.compose.material3.CircularProgressIndicator() }
}

@Composable
fun ErrorShell(
    message: String,
    onRetry: () -> Unit
) {
    ApplicationConfig.config.errorScreen(message) { onRetry() }
}