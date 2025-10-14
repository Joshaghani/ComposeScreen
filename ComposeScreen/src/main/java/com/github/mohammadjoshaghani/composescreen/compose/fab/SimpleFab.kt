package com.github.mohammadjoshaghani.composescreen.compose.fab

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.compose.component.UIIcon

@Composable
internal fun SimpleFab(fabIconModel: FabIconModel) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = fabIconModel.onFabPressed
    ) {
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

    }
}

