package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen

@Composable
fun AppContent(startScreen: RootScreen<*, *, *, *>) {

    AppParentContent {
        RenderScreenContent(startScreen)
        RenderNotifications()
    }

    if (Navigator.state.current.value == null) {
        startScreen.show(animation = false)
    }

}



