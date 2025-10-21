package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Composable
fun MeasureHeight(
    onHeightChanged: (Dp) -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    val density = LocalDensity.current
    var lastHeight by remember { mutableStateOf(0.dp) }

    Box(
        Modifier.onGloballyPositioned { coords ->
            val newHeight = with(density) { coords.size.height.toDp() }
            if (abs(newHeight.value - lastHeight.value) > 0.5f) {
                lastHeight = newHeight
                onHeightChanged(newHeight)
            }

        }) {
        content(Modifier)
    }
}