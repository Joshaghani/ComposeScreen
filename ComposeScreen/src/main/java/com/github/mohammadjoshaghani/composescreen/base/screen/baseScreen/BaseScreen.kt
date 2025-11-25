package com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.snapshotFlow
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.compose.ContentScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen
import com.github.mohammadjoshaghani.composescreen.compose.UIAnimatedVisibility
import com.github.mohammadjoshaghani.composescreen.compose.topbar.TopBar
import kotlinx.coroutines.flow.distinctUntilChanged

abstract class BaseScreen<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> :
    RootScreen<State, Event, Effect, VM>(), IScreenInitializer<State, Event> {
    var mainScrollState: ScrollState? = null

    private var scrollPositionBaseScreen = mutableIntStateOf(0)

    lateinit var padding: PaddingValues

    @Composable
    override fun ShowScreenFromApp(padding: PaddingValues) {
        this.padding = padding
        UIAnimatedVisibility {
            super.SetStateComposeScreen(this@BaseScreen)
        }
    }

    @Composable
    override fun InitBaseComposeScreen(state: State) {
        val scrollState = rememberScrollState(scrollPositionBaseScreen.value)
        mainScrollState = scrollState

        LaunchedEffect(scrollState) {
            snapshotFlow {
                scrollState.value > 0
            }.distinctUntilChanged().collect { lifted ->
                TopBar.isLifted.value = lifted
            }
        }

        ContentScreen()

    }

    override fun onPause() {
        super.onPause()
        scrollPositionBaseScreen.intValue = mainScrollState?.value ?: 0
    }
}