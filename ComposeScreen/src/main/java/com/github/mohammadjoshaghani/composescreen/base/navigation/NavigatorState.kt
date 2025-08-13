package com.github.mohammadjoshaghani.composescreen.base.navigation

import androidx.compose.runtime.mutableStateOf
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen

class NavigatorState {

    private val _current = mutableStateOf<RootScreen<*,*,*,*>?>(null)
    val current: androidx.compose.runtime.State<RootScreen<*,*,*,*>?> get() = _current

    internal fun set(value: RootScreen<*,*,*,*>?) {
        _current.value = value
    }
}