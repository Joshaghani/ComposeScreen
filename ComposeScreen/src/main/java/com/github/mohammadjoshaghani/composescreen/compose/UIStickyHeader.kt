package com.github.mohammadjoshaghani.composescreen.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.MeasureHeight
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen
import com.github.mohammadjoshaghani.composescreen.compose.topbar.TopBar
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@Composable
fun UIStickyHeader(
    screen: RootScreen<*, *, *, *>,
    content: @Composable (Modifier) -> Unit,
) {
    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = ApplicationConfig.config.color.background,
            shadowElevation = if (TopBar.Companion.isScrolled.value) 5.dp else 0.dp
        ) {
            MeasureHeight(onHeightChanged = { h ->
                if (screen.stickyState.stickyHeaderHeight != h) screen.stickyState.stickyHeaderHeight =
                    h
            }) { measuredModifier ->
                content(measuredModifier)
            }

        }
        if (ApplicationConfig.config.isDarkTheme && TopBar.Companion.isScrolled.value) {
            HorizontalDivider()
        }
    }
}
