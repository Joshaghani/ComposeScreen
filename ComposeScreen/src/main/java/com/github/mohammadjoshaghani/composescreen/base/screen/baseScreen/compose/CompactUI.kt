package com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.RunIfShowAwareHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.BaseScreen


@Composable
fun BaseScreen<*, *, *, *>.CompactUI() {
    Column(
        modifier = Modifier.Companion
            .fillMaxSize()
            .height(this@CompactUI.maxHeight)
            .verticalScroll(mainScrollState!!)
    ) {
        RunIfShowAwareHeader {
            Spacer(Modifier.height(if (showAwareHeader.value) heightAwareFaideHeader.value else 0.dp))
        }

        if (this@CompactUI is IShowStickyHeader) {
            Spacer(Modifier.height(stickyState.stickyHeaderHeight))
        }

        ComposeView(viewModel.viewState.value)
    }

}