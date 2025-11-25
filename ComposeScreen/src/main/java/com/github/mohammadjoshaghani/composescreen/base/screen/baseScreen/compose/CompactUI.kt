package com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.BaseScreen


@Composable
fun BaseScreen<*, *, *, *>.CompactUI() {
    Column(
        modifier = Modifier.Companion
            .fillMaxSize()
            .height(screenSize.value.height)
            .verticalScroll(mainScrollState!!)
    ) {

        Spacer(Modifier.height(heightAwareFaideHeader.value))

        if (this@CompactUI is IShowStickyHeader) {
            Spacer(Modifier.height(stickyHeaderHeight.value))
        }

        ComposeView(viewModel.viewState.value)

        Spacer(modifier = Modifier.padding(bottom = padding.calculateBottomPadding()))
    }
}