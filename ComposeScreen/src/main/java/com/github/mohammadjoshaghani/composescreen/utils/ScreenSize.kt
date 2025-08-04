package com.github.mohammadjoshaghani.composescreen.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ScreenSize(
    val width: Dp,
    val height: Dp,
) {
    var availableHeight: Dp = 0.dp
}