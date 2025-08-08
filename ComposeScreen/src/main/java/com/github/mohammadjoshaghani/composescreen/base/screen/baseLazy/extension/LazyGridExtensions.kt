package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.mohammadjoshaghani.composescreen.base.handler.ILazyLoadingList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe.EmptyListContent
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe.LoadMoreProgressbar
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.isLoadMoreList


// --- LazyVerticalGrid itemsIndexed
fun <T> LazyGridScope.renderItemsIndexed(
    list: List<T>,
    key: ((index: Int, item: T) -> Any)? = null,
    content: @Composable (index: Int, item: T) -> Unit,
) {
    if (key != null) {
        itemsIndexed(list, key = key) { index, item ->
            content(index, item)
        }
    } else {
        itemsIndexed(list) { index, item ->
            content(index, item)
        }
    }
}

fun <T> LazyGridScope.renderLoadMore(
    list: MutableList<T>,
    screen: BaseScreenLazyList<*, *, *, *>,
) {
    if (screen is ILazyLoadingList) {
        item {
            LaunchedEffect(true) {
                if (isLoadMoreList(list)) screen.lazyColumnScrolledEnd()
            }
        }
        if (list.isNotEmpty() && list.size % 10 == 0) {
            item { LoadMoreProgressbar() }
        }
    }
}

fun <T> LazyGridScope.renderEmptyState(
    list: MutableList<T>,
    screen: BaseScreenLazyList<*, *, *, *>,
) {
    if (list.isEmpty()) {
        item {
            screen.EmptyListContent()
        }
    }
}



