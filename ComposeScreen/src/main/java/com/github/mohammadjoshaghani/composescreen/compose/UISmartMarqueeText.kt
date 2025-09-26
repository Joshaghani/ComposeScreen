package com.github.mohammadjoshaghani.composescreen.compose

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun UISmartMarqueeText(
    text: String,
    width: Dp = 200.dp,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    contentAlignment: Alignment = Alignment.Center,
) {
    val textMeasurer = rememberTextMeasurer()
    val context = LocalDensity.current

    var isOverflowing by remember { mutableStateOf(false) }

    LaunchedEffect(text, width, textStyle) {
        val textLayoutResult = textMeasurer.measure(
            text = text,
            style = textStyle
        )
        val textWidthPx = textLayoutResult.size.width.toFloat()
        val boxWidthPx = with(context) { width.value }

        isOverflowing = textWidthPx > boxWidthPx
    }

    Box(
        modifier = Modifier.width(width),
        contentAlignment = contentAlignment
    ) {
        Text(
            text = text,
            style = textStyle,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            modifier = if (isOverflowing) Modifier.basicMarquee() else Modifier
        )
    }
}