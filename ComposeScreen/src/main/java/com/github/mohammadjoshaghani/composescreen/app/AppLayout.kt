package com.github.mohammadjoshaghani.composescreen.app


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.layout.Layout
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize

val screenSize = mutableStateOf(ScreenSize(-1, -1))

@Composable
fun AppLayout(content: @Composable () -> Unit) {
    Layout(
        content = content,
        measurePolicy = { measurables, constraints ->
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            screenSize.value = ScreenSize(width, height)

            val placeables = measurables.map { it.measure(constraints) }

            layout(width, height) {
                var yPosition = 0
                placeables.forEach { placeable ->
                    placeable.placeRelative(x = 0, y = yPosition)
                    yPosition += placeable.height
                }
            }
        }
    )
}