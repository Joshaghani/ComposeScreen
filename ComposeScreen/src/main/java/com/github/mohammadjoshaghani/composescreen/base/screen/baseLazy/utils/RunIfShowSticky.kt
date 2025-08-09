package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen


@Composable
fun RootScreen<*, *, *, *>.RunIfShowSticky(
    isNotStickyHeader: (@Composable IShowStickyHeader.() -> Unit) = { },
    content: @Composable IShowStickyHeader.() -> Unit,
) {
    if (this is IShowStickyHeader) {
        val isPermission by isPermissionShowSticky.collectAsState()
        if (isPermission) {
            content()
        } else {
            isNotStickyHeader
        }
    }
}

@Composable
fun RootScreen<*, *, *, *>.RunIfShowStickyBoolean(
    content: @Composable (Boolean) -> Unit,
) {
    if (this is IShowStickyHeader) {
        val isPermission by isPermissionShowSticky.collectAsState()
        content(isPermission)
    }
}


fun RootScreen<*, *, *, *>.isStickyBoolean(): Boolean {
    return this is IShowStickyHeader
}