package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.commonCompose.fab.UIFab
import com.github.mohammadjoshaghani.composescreen.commonCompose.topbar.TopBar
import com.github.mohammadjoshaghani.composescreen.extension.clickableWitoutHighlight

private var keyboardController: SoftwareKeyboardController? = null
private var focusManager: FocusManager? = null

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderScreenContent() {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    keyboardController = LocalSoftwareKeyboardController.current
    focusManager = LocalFocusManager.current

    Scaffold(
        floatingActionButton = { UIFab() },
        topBar = { ProvideLayoutDirection { TopBar().Show(scrollBehavior) } },
        bottomBar = { ProvideLayoutDirection { BottomBarRender() } },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { padding ->
        ProvideLayoutDirection {
            Column(
                modifier = Modifier
                    .padding(top = padding.calculateTopPadding())
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .clickableWitoutHighlight {
                        hideKeyboard()
                    }
            ) {
                Navigator.currentScreen.value?.let { screen ->
                    screen.ShowScreenFromApp()
                    screen.isVisibleAnimation.value = true
                }
            }
        }
    }
}

fun hideKeyboard() {
    keyboardController?.hide()
    focusManager?.clearFocus()
}