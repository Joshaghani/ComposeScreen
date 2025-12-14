package com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.BaseScreenUnScrollable

@Composable
fun BaseScreenUnScrollable<*, *, *, *>.CompactUI() {
    Column(/*modifier = Modifier.fillMaxSize()*/) {
        ComposeView(viewModel.viewState.value)
//        Spacer(modifier = Modifier.padding(bottom = padding.calculateBottomPadding()))
    }
}