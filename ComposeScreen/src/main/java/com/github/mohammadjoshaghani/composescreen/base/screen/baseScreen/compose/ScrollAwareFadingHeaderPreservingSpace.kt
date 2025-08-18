package com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.BaseScreen


@Composable
fun BaseScreen<*, *, *, *>.ScrollAwareFadingHeaderPreservingSpace() {
    if (this is IShowScrollAwareFadingHeader) {
        val density = LocalDensity.current
        var lastScrollOffset by remember { mutableStateOf(0) }
        val scrollThreshold = 0

        LaunchedEffect(mainScrollState) {
            snapshotFlow { mainScrollState!!.value }
                .collect { offset ->
                    val delta = offset - lastScrollOffset

                    when {
                        delta > scrollThreshold -> {
                            showAwareHeader.value = false
                            lastScrollOffset = offset
                        }

                        delta < -scrollThreshold -> {
                            showAwareHeader.value = true
                            lastScrollOffset = offset
                        }
                    }
                }
        }


        AnimatedVisibility(
            visible = showAwareHeader.value,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
        ) {
            UIScrollAwareFadingHeader(
                modifier = Modifier.onGloballyPositioned {
                    val newHeight = with(density) { it.size.height.toDp() }
                    if (heightAwareFaideHeader.value != newHeight) {
                        heightAwareFaideHeader.value = newHeight
                    }
                }
            )
        }
    }

}

