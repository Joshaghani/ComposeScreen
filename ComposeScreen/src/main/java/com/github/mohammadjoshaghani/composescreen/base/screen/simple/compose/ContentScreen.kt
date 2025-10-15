package com.github.mohammadjoshaghani.composescreen.base.screen.simple.compose

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.mohammadjoshaghani.composescreen.base.screen.simple.BaseSimpleScreen
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeBus

@Composable
fun BaseSimpleScreen.ContentScreen() {

    val stateSize by WindowSizeBus.windowSizeClass.collectAsState()

    when (stateSize) {
        WindowWidthSizeClass.Compact -> {
            CompactUI()
        }

        WindowWidthSizeClass.Medium -> {
            CompactUI()
        }

        WindowWidthSizeClass.Expanded -> {
            ExpandedUI {
                CompactUI()
            }
        }

    }

}
