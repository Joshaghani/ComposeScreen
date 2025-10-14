package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderItemsIndexed
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderLoadMore
import com.github.mohammadjoshaghani.composescreen.compose.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.compose.topbar.TopBar
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun <State : ViewState<Event>, Event : ViewEvent> BaseScreenLazyList<State, *, *, *>.UILazyColumn(
    state: State,
    modifier: Modifier = Modifier,
) {

    val listState = LazyListState(firstVisibleItemIndex = scrollPositionListScreen)
    lazyListState = listState

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.firstVisibleItemScrollOffset > 1
        }.distinctUntilChanged().collect { lifted ->
            TopBar.isLifted.value = lifted
        }
    }

    LazyColumn(
        state = lazyListState!!, modifier = modifier
    ) {
        item {
            Spacer(modifier = Modifier.height(stickyHeaderHeight.value))
            Spacer(Modifier.height(heightAwareFaideHeader.value))
        }

        item {
            ComposeView(state)
        }
        val list = getItemsList(state)

        if (list.isEmpty()) {
            item { EmptyListUI(state) }
        } else {
            renderItemsIndexed(list) { index, item ->
                ItemUI(state, index, item)
            }
        }
        renderLoadMore(list, lazyListState!!, this@UILazyColumn)
        item { FooterUI(state) }
        item { UISpacer(if (this@UILazyColumn.iconFab() != null) 150 else 50) }
    }
}