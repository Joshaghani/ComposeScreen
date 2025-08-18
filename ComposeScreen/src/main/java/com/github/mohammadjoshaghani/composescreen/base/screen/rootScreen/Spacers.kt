package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StickySpacer(state: StickyHeaderState) {
    if (state.hasStickyHeader.collectAsState().value) {
        Spacer(
            Modifier.height(state.stickyHeaderHeight)
        )
    }
}

@Composable
fun AwareHeaderSpacer(showAware: Boolean, height: Dp) {
    Spacer(
        Modifier.height(if (showAware) height else 0.dp)
    )
}