package com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.compose

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.BaseScreenUnScrollable
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeBus


@Composable
fun BaseScreenUnScrollable<*, *, *, *>.ContentScreen() {


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