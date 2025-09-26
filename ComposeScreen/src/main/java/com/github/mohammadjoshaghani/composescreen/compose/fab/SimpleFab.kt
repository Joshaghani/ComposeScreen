package com.github.mohammadjoshaghani.composescreen.compose.fab

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource

@Composable
internal fun SimpleFab(iconId: Int, onClick: () -> Unit) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = null,
            tint = White
        )
    }
}

