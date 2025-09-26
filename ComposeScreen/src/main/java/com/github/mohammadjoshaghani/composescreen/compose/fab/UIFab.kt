package com.github.mohammadjoshaghani.composescreen.compose.fab

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowFab
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.compose.UIAnimatedVisibility

@Composable
fun UIFab() {
    val currentScreen = Navigator.state.current.value ?: return

    if (currentScreen !is IShowFab) return

    val viewState = currentScreen.viewModel.viewState.value
    if (viewState.errorScreen != null || viewState.isLoading) return

    val fabIcon = currentScreen.iconFab() ?: return

    UIAnimatedVisibility {
        if (fabIcon.title.isNullOrEmpty()) {
            SimpleFab(iconId = fabIcon.iconId, onClick = fabIcon.onFabPressed)
        } else {
            ExtendedFab(
                iconId = fabIcon.iconId,
                title = fabIcon.title,
                onClick = fabIcon.onFabPressed
            )
        }
    }
}

