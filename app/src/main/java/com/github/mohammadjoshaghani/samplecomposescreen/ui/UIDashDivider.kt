package com.github.mohammadjoshaghani.samplecomposescreen.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun UIDashDivider(
    modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.onSecondary
) {
    Canvas(
        modifier.padding(horizontal = 8.dp).height(1.dp)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(10f, 10f), 0f
            )
        )
    }
}

@Composable
fun DashDividerVertical(
    modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.onSecondary
) {
    Canvas(
        modifier.width(10.dp).padding(vertical = 4.dp)
    ) {
        val canvasWidth = size.width
        drawLine(
            strokeWidth = 5.dp.toPx(),
            cap = StrokeCap.Round,
            color = color,
            start = Offset(x = canvasWidth / 2, y = 0f),
            end = Offset(x = canvasWidth / 2, y = size.height),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(
                    10f, // important!
                    8.dp.toPx(), // must be greater than stroke width
                )
            )
        )
    }
}