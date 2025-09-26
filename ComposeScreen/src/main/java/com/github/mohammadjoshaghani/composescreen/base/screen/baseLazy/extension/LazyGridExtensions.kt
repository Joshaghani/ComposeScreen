package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import com.github.mohammadjoshaghani.composescreen.base.handler.IIdentifiable
import com.github.mohammadjoshaghani.composescreen.base.handler.ILazyLoadingList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe.LoadMoreProgressbar
import kotlinx.coroutines.flow.distinctUntilChanged


fun <T : IIdentifiable> LazyGridScope.renderItemsIndexed(
    list: List<T>,
    content: @Composable (index: Int, item: T) -> Unit,
) {
    itemsIndexed(
        list, key = { _, item -> item.id }) { index, item ->
        content(index, item)
    }
}

fun <T> LazyGridScope.renderLoadMore(
    list: MutableList<T>,
    lazyGridState: LazyGridState,
    screen: BaseScreenLazyList<*, *, *, *>,
) {
    if (screen is ILazyLoadingList) {
        item {
            LoadMoreOnScroll(lazyGridState) {
                screen.lazyColumnScrolledEnd()
            }
        }
        if (list.isNotEmpty() && list.size % 10 == 0) {
            item { LoadMoreProgressbar() }
        }
    }
}

@Composable
fun LoadMoreOnScroll(lazyListState: LazyGridState, onLoadMore: () -> Unit) {
    LaunchedEffect(lazyListState) {
        snapshotFlow {
            val info = lazyListState.layoutInfo
            val lastVisible = info.visibleItemsInfo.lastOrNull()?.index ?: -1
            lastVisible to info.totalItemsCount
        }.distinctUntilChanged()
            .collect { (lastVisible, total) ->
                val isAtEnd = total > 0 && lastVisible >= total - 1
                if (isAtEnd) onLoadMore()
            }
    }
}




