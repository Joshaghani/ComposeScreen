package com.github.mohammadjoshaghani.composescreen.commonCompose.awareFading

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList


@Composable
fun UIScrollAwareFading(
    screen: BaseScreenLazyList<*, *, *, *>,
    modifier: Modifier = Modifier,
    contentItemRows: @Composable () -> Unit,
    stickyheadContent: @Composable () -> Unit,
    fadeHeaderContent: @Composable (Modifier) -> Unit,
) {

    val density = LocalDensity.current

    screen.viewModel.launchOnScope {
        screen.scrollEvents.collect {
            screen.showAwareHeader = it
        }
    }

    Box(modifier) {

        // پایین ترین
        contentItemRows()

        // بالای کانتنت
        AnimatedVisibility(
            visible = screen.showAwareHeader,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
        ) {
            Column {
                if (screen.isPermissionShowSticky.value)
                    Spacer(modifier = Modifier.height(screen.heightStickyHeader.value))
                fadeHeaderContent(
                    Modifier
                        .onGloballyPositioned {
                            screen.heightAwareFaideHeader.value =
                                with(density) { it.size.height.toDp() }
                        }
                )
            }
        }

        // بالاترین سطح
        if (screen.isPermissionShowSticky.value) {
            stickyheadContent()
        }

    }
}