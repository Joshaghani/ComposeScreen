package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.compose.UIRefreshableContent
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeBus
import kotlinx.coroutines.delay

@Composable
fun <State : ViewState<Event>, Event : ViewEvent>
        BaseScreenLazyList<State, *, *, *>.ContentScreen(state: State) {

    UIRefreshableContent {

        ScrollAwareFadingHeaderWrappeUI(this@ContentScreen) {

            var showItem by remember { mutableStateOf(false) }

            // یک فریم صبر کن تا استیکی یا فید هدر ساخته بشن
            LaunchedEffect(Unit) {
                delay(16)
                showItem = true
            }

            if (showItem) {
                val stateSize by WindowSizeBus.windowSizeClass.collectAsState()

                when (stateSize) {
                    WindowWidthSizeClass.Compact -> {
                        CompactUI(state)
                    }

                    WindowWidthSizeClass.Medium -> {
                        CompactUI(state)
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
}

