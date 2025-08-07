package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowFab
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderEmptyState
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderItemsIndexed
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderListHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderLoadMore
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIRefreshableContent
import com.github.mohammadjoshaghani.composescreen.commonCompose.UISpacer
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeClass


@Composable
fun <State : ViewState<Event>, Event : ViewEvent>
        BaseScreenLazyList<State, *, *, *>.ContentScreen(state: State) {
    UIRefreshableContent {
        val sizeClass = remember(this.maxWidth) {
            windowSizeClass.value = WindowSizeClass.Companion.fromWidth(maxWidth)
            windowSizeClass.value
        }

        ScrollAwareFadingHeaderWrappeUI(
            this@ContentScreen,
            isStickyHeader.value,
            stickyContent = {
                ComposeView(state)
            }) {
            when (sizeClass) {
                WindowSizeClass.Compact -> {
                    CompactUI(state, it)
                }

                WindowSizeClass.Medium -> {
                    MediumUI {
                        CompactUI(state, it)
                    }
                }

                WindowSizeClass.Expanded -> {
                    ExpandedUI {
                        CompactUI(state, it)
                    }
                }
            }
        }

    }
}


@Composable
private fun <State : ViewState<Event>, Event : ViewEvent> BaseScreenLazyList<State, *, *, *>.CompactUI(
    state: State,
    headerHeight: Dp,
) {

    var showHeaderAware by remember { mutableStateOf(true) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < 0) {
                    // اسکرول به پایین => هاید کن
                    if (showHeaderAware) {
                        showHeaderAware = false
//                        println("⬇️ اسکرول به پایین - مخفی کردن هدر")
                        scrollEvents.value = false
                    }
                } else if (available.y > 0) {
                    // اسکرول به بالا => نشون بده
                    if (!showHeaderAware) {
                        showHeaderAware = true
//                        println("⬆️ اسکرول به بالا - نمایش هدر")
                        scrollEvents.value = true
                    }
                }
                return Offset.Zero
            }
        }
    }

    if (verticalGridMinSize > 1) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(verticalGridMinSize.dp),
            modifier = Modifier
                .nestedScroll(nestedScrollConnection)
                .fillMaxSize()
        ) {
            renderListHeader(isSticky = isStickyHeader.value, headerHeight) {
                ComposeView(state)
            }
            if (isStickyHeader.value) {
                item {
                    Spacer(modifier = Modifier.height(headerHeight))
                }
            }
            renderItemsIndexed(getItemsList(state)) { index, item ->
                ItemUI(index, item)
            }
            renderLoadMore(getItemsList(state), this@CompactUI)
            renderEmptyState(getItemsList(state), this@CompactUI)

            item { FooterUI(state) }
            item { UISpacer(if (this@CompactUI is IShowFab) 150 else 50) }
        }
    } else {
        LazyColumn(
            state = lazyListState!!,
            modifier = Modifier
                .nestedScroll(nestedScrollConnection)
                .fillMaxSize()
        ) {
            renderListHeader(isSticky = isStickyHeader.value, headerHeight) {
                ComposeView(state)
            }
            if (isStickyHeader.value) {
                item {
                    Spacer(modifier = Modifier.height(headerHeight))
                }
            }
            renderItemsIndexed(getItemsList(state)) { index, item ->
                ItemUI(index, item)
            }
            renderLoadMore(getItemsList(state), this@CompactUI)
            renderEmptyState(getItemsList(state), this@CompactUI)
            item { FooterUI(state) }
            item { UISpacer(if (this@CompactUI is IShowFab) 150 else 50) }
        }
    }
}