package com.github.mohammadjoshaghani.composescreen.base.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIRefreshableContent
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIAnimatedVisibility
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeClass
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseScreen<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> :
    RootScreen<State, Event, Effect, VM>(), IScreenInitializer<State, Event> {

    private var mainScrollState: ScrollState? = null

    private var scrollPositionBaseScreen = mutableIntStateOf(0)

    var maxHeight: Dp = 0.dp

    var windowSizeClass = MutableStateFlow(WindowSizeClass.Expanded)

    @Composable
    override fun ShowScreenFromApp() {
        UIAnimatedVisibility {
            super.SetStateComposeScreen(this)
        }
    }

    @Composable
    override fun InitBaseComposeScreen(state: State) {
        mainScrollState = rememberScrollState()

        LaunchedEffect(scrollPositionBaseScreen) {
            mainScrollState?.scrollTo(scrollPositionBaseScreen.intValue)
        }

        UIRefreshableContent {
            this@BaseScreen.maxHeight = maxHeight
            val sizeClass = remember(this.maxWidth) {
                windowSizeClass.value = WindowSizeClass.fromWidth(maxWidth)
                windowSizeClass.value
            }

            when (sizeClass) {
                WindowSizeClass.Compact -> {
                    CompactUI()
                }

                WindowSizeClass.Medium -> {
                    MediumUI {
                        CompactUI()
                    }
                }

                WindowSizeClass.Expanded -> {
                    ExpandedUI {
                        CompactUI()
                    }
                }
            }
        }
    }

    @Composable
    private fun CompactUI() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(maxHeight)
                .verticalScroll(mainScrollState!!)
        ) {
            ComposeView(viewModel.viewState.value)
        }
    }

    override fun onPause() {
        super.onPause()
        scrollPositionBaseScreen.intValue = mainScrollState?.value ?: 0
    }
}
