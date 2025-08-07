package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.commonCompose.topbar.TopBar
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

var stickyHeaderHeight by mutableStateOf(0.dp)

@Composable
fun StickyHeaderUI(content: @Composable () -> Unit) {
    val density = LocalDensity.current
    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = ApplicationConfig.config.color.background,
            shadowElevation = if (TopBar.Companion.isScrolled.value) 5.dp else 0.dp
        ) {
            Column(
                Modifier.onGloballyPositioned {
                    stickyHeaderHeight = with(density) { it.size.height.toDp() }
                }
            ) {
                content()
            }
        }
        if (ApplicationConfig.config.isDarkTheme && TopBar.Companion.isScrolled.value) {
            HorizontalDivider()
        }
    }
}
