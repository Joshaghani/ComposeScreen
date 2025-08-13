package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MeasureHeight(
    onHeightChanged: (Dp) -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    val density = LocalDensity.current
    var measuredHeight by rememberSaveable { mutableStateOf(0.dp) }

    // وقتی مقدار measuredHeight تغییر کرد، یک فریم بعد اطلاع بده
    LaunchedEffect(measuredHeight) {
        if (measuredHeight > 0.dp) {
            kotlinx.coroutines.delay(16) // حدود یک فریم
            onHeightChanged(measuredHeight)
        }
    }

    Box(
        modifier = Modifier.onGloballyPositioned { coordinates ->
            val newHeight = with(density) { coordinates.size.height.toDp() }
            if (newHeight != measuredHeight) {
                measuredHeight = newHeight
            }
        }
    ) {
        content(Modifier)
    }
}