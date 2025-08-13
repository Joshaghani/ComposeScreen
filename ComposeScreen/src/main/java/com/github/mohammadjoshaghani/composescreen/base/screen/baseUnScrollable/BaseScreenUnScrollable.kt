package com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IRefreshableScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.compose.ContentScreen
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIAnimatedVisibility

abstract class BaseScreenUnScrollable<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> :
    RootScreen<State, Event, Effect, VM>(), IScreenInitializer<State, Event> {

    @Composable
    override fun ShowScreenFromApp() {
        UIAnimatedVisibility {
            super.SetStateComposeScreen(this)
        }
    }

    @Composable
    override fun InitBaseComposeScreen(state: State) {
        if (this is IRefreshableScreen) {
            throw IllegalStateException(
                "This screen must not implement IRefreshableScreen. " +
                        "Use BaseScreenLazyList or BaseScreen instead BaseScreenUnScrollable if you need pull-to-refresh support."
            )
        }

        ContentScreen()
    }

}