package com.github.mohammadjoshaghani.composescreen.commonCompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import com.github.mohammadjoshaghani.composescreen.base.handler.IRefreshableScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeClass


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RootScreen<*, *, *, *>.UIRefreshableContent(content: @Composable @UiComposable BoxWithConstraintsScope.(WindowSizeClass) -> Unit) {

    val isRefreshable = this is IRefreshableScreen

    val pullToRefreshState = rememberPullToRefreshState()

    val content: @Composable BoxScope.() -> Unit = {
        BoxWithConstraints {
            val sizeClass = remember(maxWidth) {
                WindowSizeClass.fromWidth(maxWidth)
            }
            content(sizeClass)
        }
    }

    if (isRefreshable) {
        @Suppress("UNCHECKED_CAST")
        val refreshable = this as IRefreshableScreen
        val state = viewModel.viewState.value

        PullToRefreshBox(
            isRefreshing = refreshable.isRefreshing.value,
            onRefresh = {
                if (state.errorScreen == null) {
                    refreshable.refreshEvent()
                }
            },
            state = pullToRefreshState,
            modifier = Modifier.fillMaxSize(),
            indicator = {
                PullToRefreshDefaults.Indicator(
                    state = pullToRefreshState,
                    isRefreshing = refreshable.isRefreshing.value,
                    modifier = Modifier.align(Alignment.TopCenter),
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            content = content
        )
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
            content()
        }
    }
}
