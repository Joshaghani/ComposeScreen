package com.github.mohammadjoshaghani.composescreen.compose

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen

@Composable
fun BottomBarRender() {

    val currentScreen = Navigator.state.current.value ?: return

    val visible =
        currentScreen !is RootScreen<*, *, *, *> ||
                (currentScreen.viewModel.viewState.value.errorScreen == null &&
                        !currentScreen.viewModel.viewState.value.isLoading)

    UIAnimatedVisibility {
        if (visible)
            currentScreen.BottomBarView()
    }

}