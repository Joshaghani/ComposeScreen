package com.github.mohammadjoshaghani.samplecomposescreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun UIBorderCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = MaterialTheme.colorScheme.outlineVariant,
    elevation: Dp = 0.dp,
    clickable: (() -> Unit)? = null,
    paddingValue: Dp = 16.dp,
    paddingTop: Dp = 16.dp,
    borderWidth: Dp = 1.dp,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        shadowElevation = elevation,
        shape = shape,
        modifier = if (borderWidth > 0.dp) {
            modifier
                .padding(top = paddingTop)
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = shape
                )
        } else {
            modifier.padding(top = paddingTop)
        }
    ) {
        if (clickable != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // rememberRipple(color = MaterialTheme.colorScheme.primary)
                    ) {
                        clickable()
                    }.padding(paddingValue), content = content
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(paddingValue),
                content = content
            )
        }
    }
}
