package com.github.mohammadjoshaghani.composescreen.commonCompose.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbar
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbarMain
import com.github.mohammadjoshaghani.composescreen.base.screen.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig


class TopBar {

    val screen = Navigator.currentScreen.value

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Show(scrollBehavior: TopAppBarScrollBehavior) {
        isScrolled.value = scrollBehavior.state.contentOffset < 0

        val elevation = if (
            isScrolled.value &&
            (screen !is BaseScreenLazyList || !screen.isStickyHeader)
        ) 5.dp else 0.dp

        Surface(
            shadowElevation = elevation,
        ) {
            Column(
                Modifier
                    .background(ApplicationConfig.config.color.background)
            ) {
                when (screen) {
                    is IShowTopbarMain -> ShowTitleMain(scrollBehavior, isScrolled.value)
                    is IShowTopbar -> ShowTitle(scrollBehavior, isScrolled.value)
                }
            }

        }
    }

    companion object {
        val isScrolled = mutableStateOf(false)
    }

}

