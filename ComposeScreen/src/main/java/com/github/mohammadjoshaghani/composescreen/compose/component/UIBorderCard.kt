package com.github.mohammadjoshaghani.composescreen.compose.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.extension.themeClickable

@Composable
fun UIBorderCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = MaterialTheme.colorScheme.outlineVariant,
    elevation: Dp = 0.dp,
    clickable: (() -> Unit)? = null,
    paddingValue: Dp = 16.dp,
    borderWidth: Dp = 1.dp,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(elevation),
        shape = shape,
        modifier = if (borderWidth > 0.dp) {
            modifier
                .padding(paddingValue)
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = shape
                )
        } else {
            modifier.padding(0.dp)
        },
    ) {

        if (clickable != null) {
            Column(
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
                modifier = Modifier
                    .clip(shape)
                    .themeClickable {
                        clickable()
                    }.padding(16.dp), content = content
            )
        } else {
            Column(
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
                modifier = Modifier
                    .padding(16.dp),
                content = content
            )
        }

    }
}


