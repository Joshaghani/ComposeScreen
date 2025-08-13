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
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.IRefreshableScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
internal fun RootScreen<*, *, *, *>.UIRefreshableContent(content: @Composable @UiComposable BoxWithConstraintsScope.() -> Unit) {

    val isRefreshable = this is IRefreshableScreen

    val pullToRefreshState = rememberPullToRefreshState()

    val content: @Composable BoxScope.() -> Unit = {
        BoxWithConstraints {
            LaunchedEffect(maxWidth, maxHeight) {
                if (screenSize.value.height == 0.dp) {
                    val width = maxWidth
                    val height = maxHeight
                    screenSize.value = ScreenSize(width, height)
                }
            }

            content()

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

