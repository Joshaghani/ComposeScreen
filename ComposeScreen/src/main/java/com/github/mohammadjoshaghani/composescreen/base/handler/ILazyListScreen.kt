package com.github.mohammadjoshaghani.composescreen.base.handler

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState


interface ILazyListScreen<State : ViewState<Event>, Event : ViewEvent> {
    fun getItemsList(state: State): MutableList<IIdentifiable>

    @Composable
    fun ItemUI(index: Int, item: Any)

    @Composable
    fun FooterUI(state: State) {
    }
}
