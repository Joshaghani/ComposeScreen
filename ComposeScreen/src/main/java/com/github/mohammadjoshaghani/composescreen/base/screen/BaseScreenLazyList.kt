package com.github.mohammadjoshaghani.composescreen.base.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.ILazyListScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.ILazyLoadingList
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowFab
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIAnimatedVisibility
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIRefreshableContent
import com.github.mohammadjoshaghani.composescreen.commonCompose.UISpacer
import com.github.mohammadjoshaghani.composescreen.commonCompose.topbar.TopBar
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

abstract class BaseScreenLazyList<
        State : ViewState<Event>,
        Event : ViewEvent,
        Effect : ViewSideEffect,
        VM : BaseViewModel<Event, State, Effect>,
        > : RootScreen<State, Event, Effect, VM>(), IScreenInitializer<State, Event>,
    ILazyListScreen<State, Event> {

    protected var warningMessageEmptyList = "لیست خالی می‌باشد!"
    open val isStickyHeader = mutableStateOf(false)
    open val verticalGridMinSize = 1

    val windowSizeClass = MutableStateFlow(WindowSizeClass.Expanded)

    private var lazyListState: LazyListState? = null

    private var scrollPositionListScreen = 0

    @Composable
    override fun ShowScreenFromApp() {
        UIAnimatedVisibility {
            super.SetStateComposeScreen(this@BaseScreenLazyList)
        }
    }

    @OptIn(
        ExperimentalUuidApi::class
    )
    @Composable
    override fun InitBaseComposeScreen(state: State) {
        lazyListState =
            rememberLazyListState(initialFirstVisibleItemIndex = scrollPositionListScreen)

        UIRefreshableContent {
            val sizeClass = remember(this.maxWidth) {
                windowSizeClass.value = WindowSizeClass.fromWidth(maxWidth)
                windowSizeClass.value
            }

            when (sizeClass) {
                WindowSizeClass.Compact -> {
                    CompactUI(state)
                }

                WindowSizeClass.Medium -> {
                    MediumUI {
                        CompactUI(state)
                    }
                }

                WindowSizeClass.Expanded -> {
                    ExpandedUI {
                        CompactUI(state)
                    }
                }
            }
        }

    }

    @Composable
    private fun CompactUI(state: State) {
        if (verticalGridMinSize > 1) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(verticalGridMinSize.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                renderHeader(viewModel.viewState.value)
                renderItems(state)
                renderLoadMore(state)
                renderEmptyState(state)
                item { FooterUI(state) }
                item { UISpacer(if (this@BaseScreenLazyList is IShowFab) 150 else 50) }
            }
        } else {
            LazyColumn(
                state = lazyListState!!, modifier = Modifier.fillMaxSize()
            ) {
                renderHeader(viewModel.viewState.value)
                renderItems(state)
                renderLoadMore(state)
                renderEmptyState(state)
                item { FooterUI(state) }
                item { UISpacer(if (this@BaseScreenLazyList is IShowFab) 150 else 50) }
            }
        }
    }

    private fun LazyListScope.renderHeader(state: State) {
        if (isStickyHeader.value) {
            stickyHeader {
                Column {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = ApplicationConfig.config.color.background,
                        shadowElevation = if (TopBar.isScrolled.value) 5.dp else 0.dp
                    ) {
                        ComposeView(state)
                    }
                    if (ApplicationConfig.config.isDarkTheme && TopBar.isScrolled.value) {
                        HorizontalDivider()
                    }
                }
            }
        } else {
            item {
                ComposeView(state)
            }
        }
    }

    private fun LazyGridScope.renderHeader(state: State) {
        if (isStickyHeader.value) {
            stickyHeader {
                Column {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = ApplicationConfig.config.color.background,
                        shadowElevation = if (TopBar.isScrolled.value) 5.dp else 0.dp
                    ) {
                        ComposeView(state)
                    }
                    if (ApplicationConfig.config.isDarkTheme && TopBar.isScrolled.value) {
                        HorizontalDivider()
                    }
                }
            }
        } else {
            item {
                ComposeView(state)
            }
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun LazyListScope.renderItems(state: State) {
        itemsIndexed(
            getItemsList(state), key = { _, item -> item.id ?: Uuid.random() }) { index, item ->
            ItemUI(index, item)
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun LazyGridScope.renderItems(state: State) {
        itemsIndexed(
            getItemsList(state), key = { _, item -> item.id ?: Uuid.random() }) { index, item ->
            ItemUI(index, item)
        }
    }

    private fun LazyListScope.renderLoadMore(state: State) {
        if (this@BaseScreenLazyList is ILazyLoadingList) {
            item {
                LaunchedEffect(true) {
                    if (isLoadMoreList(getItemsList(state))) lazyColumnScrolledEnd()
                }
            }
            if (getItemsList(state).isNotEmpty() && getItemsList(state).size % 10 == 0) {
                item { LoadMoreProgressbar() }
            }
        }
    }

    private fun LazyGridScope.renderLoadMore(state: State) {
        if (this@BaseScreenLazyList is ILazyLoadingList) {
            item {
                LaunchedEffect(true) {
                    if (isLoadMoreList(getItemsList(state))) lazyColumnScrolledEnd()
                }
            }
            if (getItemsList(state).isNotEmpty() && getItemsList(state).size % 10 == 0) {
                item { LoadMoreProgressbar() }
            }
        }
    }

    private fun LazyListScope.renderEmptyState(state: State) {
        if (getItemsList(state).isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(86.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .padding(top = 24.dp)
                            .height(86.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            warningMessageEmptyList,
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }

    private fun LazyGridScope.renderEmptyState(state: State) {
        if (getItemsList(state).isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(86.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .padding(top = 24.dp)
                            .height(86.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            warningMessageEmptyList,
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun LoadMoreProgressbar() {
        Row(
            Modifier
                .padding(32.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    override fun ComposeView(state: State) {
    }

    override fun onPause() {
        super.onPause()
        scrollPositionListScreen = lazyListState?.firstVisibleItemIndex ?: 0
    }

    private fun isLoadMoreList(list: MutableList<*>?): Boolean {

        if (list == null) {
            return false
        }

        if (list.isEmpty()) {
            return false
        }

        return list.size % 10 == 0
    }
}