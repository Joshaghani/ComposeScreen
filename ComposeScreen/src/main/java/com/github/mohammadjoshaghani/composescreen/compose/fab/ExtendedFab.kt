package com.github.mohammadjoshaghani.composescreen.compose.fab

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import com.github.mohammadjoshaghani.composescreen.compose.component.UIIcon

@Composable
internal fun ExtendedFab(fabIconModel: FabIconModel) {
    ExtendedFloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = fabIconModel.onFabPressed,
        icon = {
            if (fabIconModel.iconResource != null) {
                UIIcon(
                    fabIconModel.iconResource,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            } else if (fabIconModel.iconVector != null) {
                UIIcon(
                    fabIconModel.iconVector,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        text = {
            fabIconModel.title?.let {
                Text(
                    text = fabIconModel.title,
                    fontWeight = FontWeight.ExtraBold,
                    color = White
                )
            }
        }
    )
}