package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen

@Composable
fun AppContent(startScreen: RootScreen<*, *, *, *>) {

    AppParentContent {
        RenderScreenContent(startScreen)
        RenderNotifications()
    }

    if (Navigator.getCurrentScreen() == null) {
        startScreen.show(animation = false)
    }

}



