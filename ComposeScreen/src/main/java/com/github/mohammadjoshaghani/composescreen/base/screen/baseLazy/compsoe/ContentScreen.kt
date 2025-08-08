package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIRefreshableContent

@Composable
fun <State : ViewState<Event>, Event : ViewEvent>
        BaseScreenLazyList<State, *, *, *>.ContentScreen(state: State) {

    UIRefreshableContent {

        ScrollAwareFadingHeaderWrappeUI(this@ContentScreen) {

            var stateSize by remember { mutableStateOf(WindowWidthSizeClass.Compact) }
            LaunchedEffect(windowSizeClass.value) {
                windowSizeClass.collect {
                    stateSize = it
                }
            }

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

