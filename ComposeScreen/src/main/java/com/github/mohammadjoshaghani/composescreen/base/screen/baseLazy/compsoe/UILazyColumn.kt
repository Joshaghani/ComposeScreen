package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowFab
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderEmptyState
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderItemsIndexed
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderLoadMore
import com.github.mohammadjoshaghani.composescreen.commonCompose.UISpacer

@Composable
fun <State : ViewState<Event>, Event : ViewEvent> BaseScreenLazyList<State, *, *, *>.UILazyColumn(
    state: State,
    modifier: Modifier = Modifier,
) {

    LazyColumn(
        state = lazyListState!!,
        modifier = modifier
    ) {
        item {
            Spacer(modifier = Modifier.height(stickyState.stickyHeaderHeight))
            Spacer(Modifier.height(heightAwareFaideHeader.value))
        }

        item {
            ComposeView(state)
        }
        renderItemsIndexed(getItemsList(state)) { index, item ->
            ItemUI(index, item)
        }
        renderLoadMore(getItemsList(state), this@UILazyColumn)
        renderEmptyState(getItemsList(state), this@UILazyColumn)
        item { FooterUI(state) }
        item { UISpacer(if (this@UILazyColumn is IShowFab) 150 else 50) }
    }
}