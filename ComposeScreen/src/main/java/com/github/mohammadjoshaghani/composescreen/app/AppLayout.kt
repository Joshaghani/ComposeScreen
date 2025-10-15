package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize

val screenSize = mutableStateOf(ScreenSize(0.dp, 0.dp))

@Composable
fun AppLayout(
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(modifier = modifier) {
        LaunchedEffect(maxWidth, maxHeight) {
            screenSize.value = ScreenSize(maxWidth, maxHeight)
        }
        content()
    }
}