package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import kotlinx.coroutines.flow.MutableStateFlow

@Stable
class StickyHeaderState(
    has: Boolean = false,
    heightDp: androidx.compose.ui.unit.Dp = 0.dp
) {
    var hasStickyHeader = MutableStateFlow(has)
        internal set
    var stickyHeaderHeight by mutableStateOf(heightDp)
        internal set
}


@Composable
fun StickyHeaderHost(
    screen: Any, // معمولاً this@RootScreen
    state: StickyHeaderState,
    content: @Composable () -> Unit
) {
    content() // محتوای اصلی

    (screen as? IShowStickyHeader)?.let { sticky ->
        state.hasStickyHeader.value = true
        MeasureHeight(onHeightChanged = { h ->
            if (state.stickyHeaderHeight != h) state.stickyHeaderHeight = h
        }) { measuredModifier ->
            sticky.ComposeStickyView(
                modifier = measuredModifier.fillMaxWidth()
            )
        }
    } ?: run {
        state.hasStickyHeader.value = false
        state.stickyHeaderHeight = 0.dp
    }
}