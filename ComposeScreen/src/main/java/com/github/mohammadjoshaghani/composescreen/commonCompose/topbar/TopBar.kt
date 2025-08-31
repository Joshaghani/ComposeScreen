package com.github.mohammadjoshaghani.composescreen.commonCompose.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbar
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbarMain
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.RunIfShowStickyBoolean
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.BaseScreen
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIAnimatedVisibility
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

class TopBar {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Show(scrollBehavior: TopAppBarScrollBehavior) {
        val screen = Navigator.state.current.value
        if (screen is BaseScreenLazyList) {
            isScrolled.value = screen.isScrolledNow.collectAsState().value
        } else if (screen is BaseScreen) {
            isScrolled.value = screen.isScrolledNow.collectAsState().value
        }

        var elevation by remember { mutableStateOf(0.dp) }

        elevation =
            if (isScrolled.value && (screen !is IShowStickyHeader)) {
                5.dp
            } else {
                0.dp
            }

        if (isScrolled.value) {
            screen?.RunIfShowStickyBoolean { isShowStickyHeader ->
                elevation = if (isShowStickyHeader) {
                    0.dp
                } else {
                    5.dp
                }
            }
        }

        UIAnimatedVisibility {
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
    }

    companion object {
        val isScrolled = mutableStateOf(false)
    }

}