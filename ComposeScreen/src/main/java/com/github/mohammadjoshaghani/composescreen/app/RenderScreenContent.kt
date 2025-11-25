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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen
import com.github.mohammadjoshaghani.composescreen.compose.fab.UIFab
import com.github.mohammadjoshaghani.composescreen.compose.navigationRail.NavigationSideBar
import com.github.mohammadjoshaghani.composescreen.compose.topbar.TopBar
import com.github.mohammadjoshaghani.composescreen.extension.noRippleClickable
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

internal var keyboardController: SoftwareKeyboardController? = null
internal var focusManager: FocusManager? = null


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderScreenContent(startScreen: IRootScreen) {

    keyboardController = LocalSoftwareKeyboardController.current
    focusManager = LocalFocusManager.current

    ProvideLayoutDirection {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            NavigationSideBar(startScreen).Show()

            AppLayout(
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                Scaffold(
                    floatingActionButton = { UIFab() },
                    topBar = { TopBar().Show() },
                    bottomBar = { BottomBarRender() },
                    contentWindowInsets = WindowInsets.safeDrawing,
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .padding(top = padding.calculateTopPadding())
                            .fillMaxSize()
                            .background(ApplicationConfig.config.color.background)
                            .noRippleClickable {
                                hideKeyboard()
                            }
                    ) {
                        Navigator.state.current.value?.let { screen ->
                            screen.ShowScreenFromApp(padding)
                            screen.isVisibleAnimation.value = true
                        }

                    }
                }
            }
        }
    }

}
