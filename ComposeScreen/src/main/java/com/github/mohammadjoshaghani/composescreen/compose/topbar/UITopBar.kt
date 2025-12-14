package com.github.mohammadjoshaghani.composescreen.compose.topbar

import androidx.compose.runtime.Composable

sealed interface UITopBar {
    data class Text(val text: String) : UITopBar
    data class Compose(val compose: @Composable () -> Unit) : UITopBar

    data object Noting : UITopBar
}