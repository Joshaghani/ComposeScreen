package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(startScreen: RootScreen<*,*,*,*>) {

    AppParentContent {
        RenderScreenContent()
        RenderNotifications()
    }

    if (Navigator.getCurrentScreen() == null) {
        startScreen.show(animation = false)
    }

}



