package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun MeasureHeight(
    onHeightChanged: (Dp) -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    val density = LocalDensity.current

    Box(
        modifier = Modifier.onGloballyPositioned { coordinates ->
            val newHeight = with(density) { coordinates.size.height.toDp() }
            onHeightChanged(newHeight)
        }) {
        content(Modifier)
    }
}