package com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IRefreshableScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIAnimatedVisibility
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeClass
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseScreenUnScrollable<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> :
    RootScreen<State, Event, Effect, VM>(), IScreenInitializer<State, Event> {

    var windowSizeClass = MutableStateFlow(WindowSizeClass.Expanded)

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

        BoxWithConstraints {
            val sizeClass = remember(this.maxWidth) {
                windowSizeClass.value = WindowSizeClass.Companion.fromWidth(maxWidth)
                windowSizeClass.value
            }

            when (sizeClass) {
                WindowSizeClass.Compact -> {
                    CompactUI(maxHeight)
                }

                WindowSizeClass.Medium -> {
                    MediumUI {
                        CompactUI(maxHeight)
                    }
                }

                WindowSizeClass.Expanded -> {
                    ExpandedUI {
                        CompactUI(maxHeight)
                    }
                }
            }
        }
    }

    @Composable
    private fun CompactUI(maxHeight: Dp) {
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .height(maxHeight)
        ) {
            ComposeView(viewModel.viewState.value)
        }
    }

}