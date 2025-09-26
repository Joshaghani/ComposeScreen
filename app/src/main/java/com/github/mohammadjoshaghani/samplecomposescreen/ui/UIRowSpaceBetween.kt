package com.github.mohammadjoshaghani.samplecomposescreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.compose.component.UISpacer

@Composable
fun UIRowSpaceBetween(
    title: String,
    content: String,
    fontSizeContent: TextUnit = MaterialTheme.typography.bodyLarge.fontSize,
    fontSizeTitle: TextUnit = MaterialTheme.typography.bodyLarge.fontSize,
    titleColor: Color = MaterialTheme.colorScheme.onTertiary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    icon: Int? = null,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        icon?.let {
            UISpacer(6)
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            UISpacer(6)
        }

        Text(text = title, color = titleColor, fontSize = fontSizeTitle)

        UIDashDivider(modifier = Modifier.weight(1f))

        Text(content, fontSize = fontSizeContent, color = contentColor)
    }
}

@Composable
fun RowSpaceBetweenMultipleLine(
    title: String,
    content: String,
    fontSizeContext: TextUnit = MaterialTheme.typography.bodyLarge.fontSize,
    titleColor: Color = MaterialTheme.colorScheme.secondary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = title, color = titleColor)
        UISpacer(8)
        UIDashDivider(
            modifier = Modifier
                .padding(top = 12.dp)
                .weight(1f)
        )

        Text(
            content,
            fontSize = fontSizeContext,
            color = contentColor,
            modifier = Modifier,
            textAlign = TextAlign.Right
        )
    }
}
