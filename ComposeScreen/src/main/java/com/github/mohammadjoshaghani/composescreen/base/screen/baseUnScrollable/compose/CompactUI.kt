package com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.BaseScreenUnScrollable

@Composable
fun BaseScreenUnScrollable<*, *, *, *>.CompactUI() {
    Column(
        modifier = Modifier.Companion
            .fillMaxSize()
            .height(screenSize.value.height)
    ) {
        ComposeView(viewModel.viewState.value)
        Spacer(modifier = Modifier.padding(bottom = padding.calculateBottomPadding()))

    }
}