package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen


@Composable
fun IRootScreen.StickyHeaderHost(
    content: @Composable () -> Unit
) {
    content() // محتوای اصلی

    (this as? IShowStickyHeader)?.let { sticky ->
        hasStickyHeader.value = true
        MeasureHeight(onHeightChanged = { h ->
            if (stickyHeaderHeight.value != h) stickyHeaderHeight.value = h
        }) { measuredModifier ->
            sticky.ComposeStickyView(
                modifier = measuredModifier.fillMaxWidth()
            )
        }
    } ?: run {
        hasStickyHeader.value = false
        stickyHeaderHeight.value = 0.dp
    }
}