package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.awareFading.UIScrollAwareFading


@Composable
internal fun <State : ViewState<Event>, Event : ViewEvent> ScrollAwareFadingHeaderWrappeUI(
    screen: BaseScreenLazyList<State, *, *, *>,
    content: @Composable () -> Unit,
) {
    if (screen is IShowScrollAwareFadingHeader) {
        UIScrollAwareFading(
            screen = screen,
            contentItemRows = content,
            stickyheadContent = { sticky ->
                UIStickyHeader(sticky) {
                    sticky.ComposeStickyView()
                }

            },
            fadeHeaderContent = { modifier ->
                screen.UIScrollAwareFadingHeader(modifier)
            }
        )
    } else {
        Column {
            content()
        }
    }
}