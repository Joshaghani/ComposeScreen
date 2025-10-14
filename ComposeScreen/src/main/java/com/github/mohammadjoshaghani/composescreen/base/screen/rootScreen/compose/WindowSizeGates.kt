package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeBus

@Composable
fun ApplyStickyVisibilityBySize(
    screen: IShowStickyHeader,
    onVisibilityChange: (Boolean) -> Unit
) {
    val stickyTarget = remember { screen.getStickyForSizeScreen() }
    val current by WindowSizeBus.windowSizeClass.collectAsState()
    LaunchedEffect(current) {
        onVisibilityChange(stickyTarget == null || current == stickyTarget)
    }
}