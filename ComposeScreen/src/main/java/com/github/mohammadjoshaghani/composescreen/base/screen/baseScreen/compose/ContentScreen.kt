package com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.RunIfShowSticky
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.BaseScreen
import com.github.mohammadjoshaghani.composescreen.compose.UIRefreshableContent
import com.github.mohammadjoshaghani.composescreen.compose.UIStickyHeader
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeBus

@Composable
fun BaseScreen<*, *, *, *>.ContentScreen() {
    UIRefreshableContent {

        Box {

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

            Column {
                StickyHeader()
                ScrollAwareFadingHeaderPreservingSpace()
            }

        }
    }
}

@Composable
private fun BaseScreen<*, *, *, *>.StickyHeader() {
    RunIfShowSticky {
        UIStickyHeader {
            ComposeStickyView(it)
        }
    }
}