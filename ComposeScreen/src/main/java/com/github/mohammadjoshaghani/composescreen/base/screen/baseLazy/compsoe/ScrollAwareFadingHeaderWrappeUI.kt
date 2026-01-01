package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.awareFading.UIScrollAwareFading
import com.github.mohammadjoshaghani.composescreen.compose.UIStickyHeader


@Composable
internal fun <State : ViewState<Event>, Event : ViewEvent> ScrollAwareFadingHeaderWrappeUI(
    screen: BaseScreenLazyList<State, *, *, *>,
    content: @Composable () -> Unit,
) {
    UIScrollAwareFading(
        stickyheadContent = { sticky ->
            screen.UIStickyHeader {
                sticky.ComposeStickyView(it)
            }

        },
        fadeHeaderContent = { modifier ->
            if (screen is IShowScrollAwareFadingHeader) {
                screen.UIScrollAwareFadingHeader(modifier)
            }
        },
        screen = screen,
        contentItemRows = content,
    )
}