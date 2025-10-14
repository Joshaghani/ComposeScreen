package com.github.mohammadjoshaghani.composescreen.base.screen.simple.compose

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.mohammadjoshaghani.composescreen.base.screen.simple.BaseSimpleScreen
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeBus

@Composable
fun BaseSimpleScreen.ContentScreen() {
    BoxWithConstraints {
        LaunchedEffect(this.maxWidth, maxHeight) {
            val width = maxWidth
            val height = maxHeight
            screenSize.value = ScreenSize(width, height)
        }

        val stateSize by WindowSizeBus.windowSizeClass.collectAsState()

        when (stateSize) {
            WindowWidthSizeClass.Compact -> {
                CompactUI(maxHeight)
            }

            WindowWidthSizeClass.Medium -> {
                CompactUI(maxHeight)
            }

            WindowWidthSizeClass.Expanded -> {
                ExpandedUI {
                    CompactUI(maxHeight)
                }
            }
        }

    }

}
