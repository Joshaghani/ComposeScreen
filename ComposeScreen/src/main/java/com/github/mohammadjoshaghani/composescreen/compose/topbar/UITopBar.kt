package com.github.mohammadjoshaghani.composescreen.compose.topbar

import androidx.compose.runtime.Composable

sealed class UITopBar {
    data class Text(val text: String) : UITopBar()
    data class Compose(val compose: @Composable () -> Unit) : UITopBar()
}