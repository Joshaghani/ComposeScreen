package com.github.mohammadjoshaghani.composescreen.base.handler

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState

interface IScreenInitializer<State : ViewState<Event>, Event : ViewEvent> {
    @Composable
    fun InitBaseComposeScreen(state: State)
}