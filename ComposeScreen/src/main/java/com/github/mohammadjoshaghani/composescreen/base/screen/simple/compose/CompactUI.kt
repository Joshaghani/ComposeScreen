package com.github.mohammadjoshaghani.composescreen.base.screen.simple.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.base.screen.simple.BaseSimpleScreen

@Composable
fun BaseSimpleScreen.CompactUI() {
    Column(
        modifier = Modifier.Companion
            .fillMaxSize()
            .height(screenSize.value.height)
    ) {
        ComposeView()
        Spacer(modifier = Modifier.padding(bottom = padding.calculateBottomPadding()))
    }
}