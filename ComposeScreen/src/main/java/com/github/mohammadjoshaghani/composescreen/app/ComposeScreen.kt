package com.github.mohammadjoshaghani.composescreen.app

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.Config

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ComposeScreen(
    startScreen: IRootScreen,
    config: Config,
) {
    ApplicationConfig.config = config
    AppContent(startScreen)
    BackHandler(onBack = config.onBack)

}







