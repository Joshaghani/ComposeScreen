package com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.BaseScreenUnScrollable


@Composable
fun BaseScreenUnScrollable<*, *, *, *>.CompactUI(maxHeight: Dp) {
    Column(
        modifier = Modifier.Companion
            .fillMaxSize()
            .height(maxHeight)
    ) {
        ComposeView(viewModel.viewState.value)
    }
}