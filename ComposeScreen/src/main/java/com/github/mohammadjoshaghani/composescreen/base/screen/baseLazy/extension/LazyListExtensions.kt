package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import com.github.mohammadjoshaghani.composescreen.base.handler.IIdentifiable
import com.github.mohammadjoshaghani.composescreen.base.handler.ILazyLoadingList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe.LoadMoreProgressbar
import kotlinx.coroutines.flow.distinctUntilChanged


// --- LazyColumn itemsIndexed
fun <T : IIdentifiable> LazyListScope.renderItemsIndexed(
    list: List<T>,
    content: @Composable (index: Int, item: T) -> Unit,
) {
    itemsIndexed(
        list, key = { _, item -> item.id }) { index, item ->
        content(index, item)
    }
}

fun <T> LazyListScope.renderLoadMore(
    list: MutableList<T>,
    lazyListState: LazyListState,
    screen: BaseScreenLazyList<*, *, *, *>,
) {
    if (screen is ILazyLoadingList) {
        if (list.isNotEmpty() && list.size % 10 == 0) {
            item {
                LoadMoreOnScroll(lazyListState) {
                    screen.lazyColumnScrolledEnd()
                }
                LoadMoreProgressbar()
            }
        }
    }
}

@Composable
fun LoadMoreOnScroll(lazyListState: LazyListState, onLoadMore: () -> Unit) {
    LaunchedEffect(lazyListState) {
        snapshotFlow {
            val info = lazyListState.layoutInfo
            val lastVisible = info.visibleItemsInfo.lastOrNull()?.index ?: -1
            lastVisible to info.totalItemsCount
        }.distinctUntilChanged().collect { (lastVisible, total) ->
            val isAtEnd = total > 0 && lastVisible >= total - 1
            if (isAtEnd) onLoadMore()
        }
    }
}






