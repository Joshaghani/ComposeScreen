package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen

import SwipeToGoBackWrapper
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.handler.IDeactiveSwipeBackHandler

@Composable
fun WithSwipeBackIfNeeded(
    host: Any,                 // this@RootScreen
    content: @Composable () -> Unit
) {
    if (host is IDeactiveSwipeBackHandler) {
        SwipeToGoBackWrapper { content() }
    } else content()
}