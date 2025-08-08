package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIBackHandler
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.Config

@Composable
fun ComposeScreen(
    startScreen: RootScreen<*, *, *, *>,
    config: Config,
) {
    ApplicationConfig.config = config
    AppContent(startScreen)
    UIBackHandler()
}







