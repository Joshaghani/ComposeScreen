package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen

@Composable
fun AppContent(startScreen: IRootScreen) {
    AppParentContent {
        RenderScreenContent(startScreen)
        RenderNotifications()
    }
    if (Navigator.state.current.value == null) {
        startScreen.show(animation = false)
    }
}


