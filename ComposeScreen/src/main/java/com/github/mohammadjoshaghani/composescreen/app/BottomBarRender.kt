package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen
import com.github.mohammadjoshaghani.composescreen.compose.UIAnimatedVisibility

@Composable
fun BottomBarRender() {

    val currentScreen = Navigator.state.current.value ?: return

    if (currentScreen is RootScreen<*, *, *, *>) {
        val viewState = currentScreen.viewModel.viewState.value
        if (viewState.errorScreen != null || viewState.isLoading) return
    }

    UIAnimatedVisibility {
        currentScreen.BottomBarView()
    }

}