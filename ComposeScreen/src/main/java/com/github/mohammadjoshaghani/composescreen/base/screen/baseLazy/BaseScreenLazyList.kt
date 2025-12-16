package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.ILazyListScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe.ContentScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen
import com.github.mohammadjoshaghani.composescreen.compose.UIAnimatedVisibility
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseScreenLazyList<
        State : ViewState<Event>,
        Event : ViewEvent,
        Effect : ViewSideEffect,
        VM : BaseViewModel<Event, State, Effect>,
        > : RootScreen<State, Event, Effect, VM>(),
    IScreenInitializer<State, Event>,
    ILazyListScreen<State, Event> {

    var warningMessageEmptyList = "لیست خالی می‌باشد!"

    var lazyListState: LazyListState? = null
    var lazyGridState: LazyGridState? = null

    val scrollEvents = MutableStateFlow(true)

    var scrollPositionListScreen = 0

    lateinit var padding: PaddingValues

    @Composable
    override fun ShowScreenFromApp(padding: PaddingValues) {
        this.padding = padding
        UIAnimatedVisibility {
            super.SetStateComposeScreen(this@BaseScreenLazyList)
        }
    }

    @Composable
    override fun InitBaseComposeScreen(state: State) {
        ContentScreen(state)
    }

    open fun withGridItems(): Dp? = null

    @Composable
    override fun ComposeView(state: State) {
    }

    override fun onPause() {
        super.onPause()
        scrollPositionListScreen = lazyListState?.firstVisibleItemIndex ?: 0
    }

}