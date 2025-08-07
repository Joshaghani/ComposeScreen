package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.ILazyListScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe.ContentScreen
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIAnimatedVisibility
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeClass
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseScreenLazyList<
        State : ViewState<Event>,
        Event : ViewEvent,
        Effect : ViewSideEffect,
        VM : BaseViewModel<Event, State, Effect>,
        > : RootScreen<State, Event, Effect, VM>(), IScreenInitializer<State, Event>,
    ILazyListScreen<State, Event> {

    var warningMessageEmptyList = "لیست خالی می‌باشد!"

    open val isStickyHeader = mutableStateOf(false)

    open val verticalGridMinSize = 1

    val windowSizeClass = MutableStateFlow(WindowSizeClass.Expanded)

    var lazyListState: LazyListState? = null

    val scrollEvents = MutableStateFlow(true)

    private var scrollPositionListScreen = 0

    @Composable
    override fun ShowScreenFromApp() {
        UIAnimatedVisibility {
            super.SetStateComposeScreen(this@BaseScreenLazyList)
        }
    }

    @Composable
    override fun InitBaseComposeScreen(state: State) {
        lazyListState =
            rememberLazyListState(initialFirstVisibleItemIndex = scrollPositionListScreen)
        ContentScreen(state)
    }

    @Composable
    override fun ComposeView(state: State) {
    }


    override fun onPause() {
        super.onPause()
        scrollPositionListScreen = lazyListState?.firstVisibleItemIndex ?: 0
    }

}