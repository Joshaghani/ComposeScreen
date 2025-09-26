package com.github.mohammadjoshaghani.composescreen.compose.fab

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

@Composable
internal fun ExtendedFab(iconId: Int, title: String, onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = onClick,
        icon = {
            Icon(
                painter = painterResource(iconId),
                contentDescription = null,
                tint = White
            )
        },
        text = {
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                color = White
            )
        }
    )
}