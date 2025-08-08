package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIRefreshableContent
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeClass

@Composable
fun <State : ViewState<Event>, Event : ViewEvent>
        BaseScreenLazyList<State, *, *, *>.ContentScreen(state: State) {

    UIRefreshableContent {
        val sizeClass = remember(this.maxWidth) {
            windowSizeClass.value = WindowSizeClass.Companion.fromWidth(maxWidth)
            windowSizeClass.value
        }

        ScrollAwareFadingHeaderWrappeUI(
            this@ContentScreen, state) {
            when (sizeClass) {
                WindowSizeClass.Compact -> {
                    CompactUI(state)
                }

                WindowSizeClass.Medium -> {
                    MediumUI {
                        CompactUI(state)
                    }
                }

                WindowSizeClass.Expanded -> {
                    ExpandedUI {
                        CompactUI(state)
                    }
                }
            }
        }

    }
}

