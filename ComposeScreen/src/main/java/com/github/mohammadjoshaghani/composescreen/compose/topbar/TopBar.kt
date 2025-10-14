package com.github.mohammadjoshaghani.composescreen.compose.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen

class TopBar {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Show() {
        val screen = Navigator.state.current.value ?: return

        if (screen is RootScreen<*, *, *, *>) {
            val viewState = screen.viewModel.viewState.value
            if (viewState.errorScreen != null || viewState.isLoading) return
        }

        ShowTitle()

    }


    companion object {
        val isLifted = mutableStateOf(false)
    }

}