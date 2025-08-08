package com.github.mohammadjoshaghani.composescreen.commonCompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.commonCompose.topbar.TopBar
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig


@Composable
fun UIStickyHeader(
    screen: IShowStickyHeader,
    content: @Composable () -> Unit,
) {
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
                    val newHeight = with(density) { it.size.height.toDp() }
                    if (screen.heightStickyHeader.value != newHeight) {
                        screen.heightStickyHeader.value = newHeight
                    }
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
