package com.github.mohammadjoshaghani.composescreen.base.navigation

import androidx.compose.runtime.mutableStateOf
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen

class NavigatorState {

    private val _current = mutableStateOf<IRootScreen?>(null)
    val current: androidx.compose.runtime.State<IRootScreen?> get() = _current

    internal fun set(value: IRootScreen?) {
        _current.value = value
    }
}