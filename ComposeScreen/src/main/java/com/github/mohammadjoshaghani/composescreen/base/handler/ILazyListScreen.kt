package com.github.mohammadjoshaghani.composescreen.base.handler

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState


interface ILazyListScreen<State : ViewState<Event>, Event : ViewEvent> {
    fun getItemsList(state: State): MutableList<IIdentifiable>

    @Composable
    fun ItemUI(state: State, index: Int, item: Any)

    @Composable
    fun FooterUI(state: State) {
    }

    @Composable
    fun EmptyListUI(state: State) {
        Text(
            "لیست خالی می باشد!",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}
