package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
fun <State : ViewState<Event>, Event : ViewEvent> BaseScreenLazyList<State, *, *, *>.UILazyVerticalGrid(
    state: State,
    modifier: Modifier = Modifier,
) {

    val listState = LazyGridState(firstVisibleItemIndex = scrollPositionListScreen)
    lazyGridState = listState

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.firstVisibleItemScrollOffset > 1
        }.distinctUntilChanged().collect { lifted ->
            TopBar.isLifted.value = lifted
        }
    }

    LazyVerticalGrid(
        state = lazyGridState!!,
        columns = GridCells.Adaptive(verticalGridMinSize),
        modifier = modifier
    ) {

        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(stickyHeaderHeight.value))
            Spacer(Modifier.height(heightAwareFaideHeader.value))
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
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
        renderLoadMore(list, lazyGridState!!, this@UILazyVerticalGrid)

        item(span = { GridItemSpan(maxLineSpan) }) {
            FooterUI(state)
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            UISpacer(if (this@UILazyVerticalGrid.iconFab() != null) 150 else 50)
        }
    }

}