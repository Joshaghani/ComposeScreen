package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.commonCompose.awareFading.UIScrollAwareFading


@Composable
internal fun ScrollAwareFadingHeaderWrappeUI(
    screen: BaseScreenLazyList<*, *, *, *>,
    isSticky: Boolean,
    stickyContent: @Composable ColumnScope.(Dp) -> Unit,
    content: @Composable (Dp) -> Unit,
) {
    if (screen is IShowScrollAwareFadingHeader) {
        UIScrollAwareFading(
            screen = screen,
            isSticky = isSticky,
            content = {
                content(it)
            },
            stickyContent = stickyContent,
            headerContent = { modifier ->
                screen.UIScrollAwareFadingHeader(modifier)
            }
        )
    } else {
        Column {
            content(0.dp)
        }
    }
}