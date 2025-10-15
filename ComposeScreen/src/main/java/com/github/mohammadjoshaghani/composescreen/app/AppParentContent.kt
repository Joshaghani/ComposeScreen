package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@Composable
fun AppParentContent(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.imePadding().fillMaxSize(),
        color = ApplicationConfig.config.color.background
    ) {
        Box(Modifier.fillMaxSize().statusBarsPadding()) {
            Box(
                Modifier.fillMaxSize().navigationBarsPadding()
            ) {
                content()
            }
        }
    }
}