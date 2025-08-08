package com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IRefreshableScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIAnimatedVisibility
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize

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

        BoxWithConstraints {
            LaunchedEffect(this.maxWidth, maxHeight) {
                val width = maxWidth
                val height = maxHeight
                screenSize.value = ScreenSize(width, height)
            }

            var stateSize by remember { mutableStateOf(WindowWidthSizeClass.Compact) }
            LaunchedEffect(windowSizeClass.value) {
                windowSizeClass.collect {
                    stateSize = it
                }
            }

            when (stateSize) {
                WindowWidthSizeClass.Compact -> {
                    CompactUI(maxHeight)
                }

                WindowWidthSizeClass.Medium -> {
                    MediumUI {
                        CompactUI(maxHeight)
                    }
                }

                WindowWidthSizeClass.Expanded -> {
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