package com.github.mohammadjoshaghani.composescreen.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.MeasureHeight
import com.github.mohammadjoshaghani.composescreen.compose.topbar.TopBar
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@Composable
fun IRootScreen.UIStickyHeader(content: @Composable (Modifier) -> Unit) {
    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = ApplicationConfig.config.color.background,
            shadowElevation = if (TopBar.isLifted.value) 5.dp else 0.dp
        ) {
            MeasureHeight(onHeightChanged = { h ->
                if (stickyHeaderHeight.value != h) stickyHeaderHeight.value = h
            }) { measuredModifier ->
                content(measuredModifier)
            }

        }

        if (ApplicationConfig.config.isDarkTheme && TopBar.isLifted.value) {
            HorizontalDivider()
        }

    }
}
