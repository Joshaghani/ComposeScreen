package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen


@Composable
fun IRootScreen.RunIfShowSticky(
    isNotStickyHeader: (@Composable IShowStickyHeader.() -> Unit) = { },
    content: @Composable IShowStickyHeader.() -> Unit,
) {
    if (this is IShowStickyHeader) {
        if (hasStickyHeader.collectAsState().value) {
            content()
        } else {
            isNotStickyHeader()
        }
    }
}

@Composable
fun IRootScreen.RunIfShowStickyBoolean(
    content: @Composable (Boolean) -> Unit,
) {
    if (this is IShowStickyHeader) {
        content(hasStickyHeader.collectAsState().value)
    }
}


fun RootScreen<*, *, *, *>.isStickyBoolean(): Boolean {
    return this is IShowStickyHeader
}