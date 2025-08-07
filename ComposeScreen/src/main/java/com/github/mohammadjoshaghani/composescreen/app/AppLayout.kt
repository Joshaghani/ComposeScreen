package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val screenSize = mutableStateOf(ScreenSize(0.dp, 0.dp))

@Composable
fun AppLayout(content: @Composable () -> Unit) {

    BoxWithConstraints {

        LaunchedEffect(this.maxWidth, maxHeight) {
            val width = maxWidth
            val height = maxHeight
            screenSize.value = ScreenSize(width, height)
        }

        content()
    }
}