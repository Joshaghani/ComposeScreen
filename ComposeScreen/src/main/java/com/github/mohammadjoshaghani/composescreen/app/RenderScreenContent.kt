package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen
import com.github.mohammadjoshaghani.composescreen.compose.fab.UIFab
import com.github.mohammadjoshaghani.composescreen.compose.navigationRail.NavigationSideBar
import com.github.mohammadjoshaghani.composescreen.compose.topbar.TopBar
import com.github.mohammadjoshaghani.composescreen.extension.clickableWitoutHighlight
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

internal var keyboardController: SoftwareKeyboardController? = null
internal var focusManager: FocusManager? = null


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderScreenContent(startScreen: RootScreen<*, *, *, *>) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    keyboardController = LocalSoftwareKeyboardController.current
    focusManager = LocalFocusManager.current


    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        if (!ApplicationConfig.config.isRtl) {
            NavigationSideBar(startScreen).Show()
        }

        AppLayout(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Scaffold(
                floatingActionButton = { UIFab() },
                topBar = { ProvideLayoutDirection { TopBar().Show(scrollBehavior) } },
                bottomBar = { ProvideLayoutDirection { BottomBarRender() } },
                contentWindowInsets = WindowInsets.safeDrawing,
                modifier = Modifier
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            ) { padding ->
                ProvideLayoutDirection {
                    Column(
                        modifier = Modifier
                            .padding(
                                top = padding.calculateTopPadding(),
                                bottom = padding.calculateBottomPadding()
                            )
                            .fillMaxSize()
                            .background(ApplicationConfig.config.color.background)
                            .clickableWitoutHighlight {
                                hideKeyboard()
                            }
                    ) {
                        Navigator.state.current.value?.let { screen ->
                            screen.ShowScreenFromApp()
                            screen.isVisibleAnimation.value = true
                        }
                    }
                }
            }
        }

        if (ApplicationConfig.config.isRtl) {
            NavigationSideBar(startScreen).Show()
        }
    }

}
