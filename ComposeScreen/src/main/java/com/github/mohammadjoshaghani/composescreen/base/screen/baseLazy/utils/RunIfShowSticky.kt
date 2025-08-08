package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen


@Composable
fun RootScreen<*, *, *, *>.RunIfShowSticky(
    isNotStickyHeader: (@Composable IShowStickyHeader.() -> Unit) = { },
    content: @Composable IShowStickyHeader.() -> Unit,
) {
    if (this is IShowStickyHeader) {
        var isPermission by remember { mutableStateOf(false) }
        LaunchedEffect(this.isPermissionShowSticky.value) {
            this@RunIfShowSticky.isPermissionShowSticky.collect {
                isPermission = it
            }
        }

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
        var isPermission by remember { mutableStateOf(false) }
        LaunchedEffect(this.isPermissionShowSticky.value) {
            isPermissionShowSticky.collect {
                isPermission = it
            }
        }
        content(isPermission)
    }
}


fun RootScreen<*, *, *, *>.isStickyBoolean() : Boolean {
    return this is IShowStickyHeader
}