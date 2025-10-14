package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.awareFading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.RunIfShowSticky
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.MeasureHeight

@Composable
fun UIScrollAwareFading(
    screen: BaseScreenLazyList<*, *, *, *>,
    modifier: Modifier = Modifier,
    contentItemRows: @Composable () -> Unit,
    stickyheadContent: @Composable (IShowStickyHeader) -> Unit,
    fadeHeaderContent: @Composable (Modifier) -> Unit,
) {
    if (screen !is IShowScrollAwareFadingHeader) return

    LaunchedEffect(Unit) {
        screen.scrollEvents.collect {
            screen.showAwareHeader.value = it
        }
    }

    Box(modifier) {

        // پایین ترین
        contentItemRows()

        // بالای کانتنت
        AnimatedVisibility(
            visible = screen.showAwareHeader.value,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
        ) {
            Column {
                screen.RunIfShowSticky {
                    Spacer(Modifier.height(screen.stickyHeaderHeight.value))
                }

                MeasureHeight(onHeightChanged = { h ->
                    if (screen.heightAwareFaideHeader.value != h) screen.heightAwareFaideHeader.value =
                        h
                }) { measuredModifier ->
                    fadeHeaderContent(measuredModifier)
                }
            }
        }

        // بالاترین سطح
        screen.RunIfShowSticky {
            stickyheadContent(this)
        }
    }
}