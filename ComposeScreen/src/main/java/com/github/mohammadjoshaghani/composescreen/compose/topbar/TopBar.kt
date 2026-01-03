package com.github.mohammadjoshaghani.composescreen.compose.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen
import com.github.mohammadjoshaghani.composescreen.compose.UIAnimatedVisibility

class TopBar {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Show() {
        val currentScreen = Navigator.state.current.value ?: return

        val visible =
            currentScreen !is RootScreen<*, *, *, *> ||
                    (currentScreen.viewModel.viewState.value.errorScreen == null &&
                            !currentScreen.viewModel.viewState.value.isLoading)

        UIAnimatedVisibility {
            if (visible)
                ShowTitle()
        }

    }


    companion object {
        val isLifted = mutableStateOf(false)
    }

}