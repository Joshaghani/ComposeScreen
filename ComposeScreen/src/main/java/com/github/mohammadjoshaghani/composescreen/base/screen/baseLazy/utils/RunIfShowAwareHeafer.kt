package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils

import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen

@Composable
fun RootScreen<*, *, *, *>.RunIfShowAwareHeader(content: @Composable IShowScrollAwareFadingHeader.() -> Unit) {
    if (this is IShowScrollAwareFadingHeader) {
            content()
    }
}