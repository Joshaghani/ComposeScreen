package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIRefreshableContent
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeBus

@Composable
fun <State : ViewState<Event>, Event : ViewEvent>
        BaseScreenLazyList<State, *, *, *>.ContentScreen(state: State) {

    UIRefreshableContent {

        ScrollAwareFadingHeaderWrappeUI(this@ContentScreen) {

            val stateSize by WindowSizeBus.windowSizeClass.collectAsState()

            when (stateSize) {
                WindowWidthSizeClass.Compact -> {
                    CompactUI(state)
                }

                WindowWidthSizeClass.Medium -> {
                    MediumUI {
                        CompactUI(state)
                    }
                }

                WindowWidthSizeClass.Expanded -> {
                    ExpandedUI {
                        CompactUI(state)
                    }
                }
            }
        }
    }
}

