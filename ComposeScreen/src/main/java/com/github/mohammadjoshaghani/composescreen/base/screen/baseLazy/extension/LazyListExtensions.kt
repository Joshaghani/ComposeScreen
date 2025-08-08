package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.mohammadjoshaghani.composescreen.base.handler.ILazyLoadingList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe.EmptyListContent
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe.LoadMoreProgressbar
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.isLoadMoreList


// --- LazyColumn itemsIndexed
fun <T> LazyListScope.renderItemsIndexed(
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

fun <T> LazyListScope.renderLoadMore(
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

fun <T> LazyListScope.renderEmptyState(
    list: MutableList<T>,
    screen: BaseScreenLazyList<*, *, *, *>,
) {
    if (list.isEmpty()) {
        item {
            screen.EmptyListContent()
        }
    }
}





