package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowBottombar

@Composable
fun BottomBarRender() {

    val currentScreen = Navigator.currentScreen.value ?: return
    val viewState = currentScreen.viewModel.viewState.value

    if (viewState.errorScreen != null || viewState.isLoading) return

    if (currentScreen is IShowBottombar) {
        currentScreen.BottomBarView()
    }

}