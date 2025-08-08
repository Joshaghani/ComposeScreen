package com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.RunIfShowAwareHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.RunIfShowSticky
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIAnimatedVisibility
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIRefreshableContent
import com.github.mohammadjoshaghani.composescreen.commonCompose.UIStickyHeader

abstract class BaseScreen<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> :
    RootScreen<State, Event, Effect, VM>(), IScreenInitializer<State, Event> {

    private var mainScrollState: ScrollState? = null

    private var scrollPositionBaseScreen = mutableIntStateOf(0)

    var maxHeight: Dp = 0.dp


    @Composable
    override fun ShowScreenFromApp() {
        UIAnimatedVisibility {
            super.SetStateComposeScreen(this)
        }
    }

    @Composable
    override fun InitBaseComposeScreen(state: State) {
        val scrollState = rememberScrollState()
        mainScrollState = scrollState

        LaunchedEffect(scrollPositionBaseScreen.intValue) {
            mainScrollState!!.scrollTo(scrollPositionBaseScreen.intValue)
        }

        UIRefreshableContent {
            this@BaseScreen.maxHeight = maxHeight

            Box {

                var stateSize by remember { mutableStateOf(WindowWidthSizeClass.Compact) }
                LaunchedEffect(windowSizeClass.value) {
                    windowSizeClass.collect {
                        stateSize = it
                    }
                }

                when (stateSize) {
                    WindowWidthSizeClass.Compact -> {
                        CompactUI()
                    }

                    WindowWidthSizeClass.Medium -> {
                        MediumUI {
                            CompactUI()
                        }
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
    private fun StickyHeader() {
        RunIfShowSticky {
            UIStickyHeader(this) {
                ComposeStickyView()
            }
        }
    }


    @Composable
    private fun ScrollAwareFadingHeaderPreservingSpace() {
        if (this is IShowScrollAwareFadingHeader) {
            val density = LocalDensity.current
            var lastScrollOffset by remember { mutableStateOf(0) }
            val scrollThreshold = 100

            LaunchedEffect(mainScrollState) {
                snapshotFlow { mainScrollState!!.value }
                    .collect { offset ->
                        val delta = offset - lastScrollOffset

                        when {
                            delta > scrollThreshold -> {
                                showAwareHeader.value = false
                                lastScrollOffset = offset
                            }

                            delta < -scrollThreshold -> {
                                showAwareHeader.value = true
                                lastScrollOffset = offset
                            }
                        }
                    }
            }


            AnimatedVisibility(
                visible = showAwareHeader.value,
                enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
            ) {
                UIScrollAwareFadingHeader(
                    modifier = Modifier.onGloballyPositioned {
                        val newHeight = with(density) { it.size.height.toDp() }
                        if (heightAwareFaideHeader.value != newHeight) {
                            heightAwareFaideHeader.value = newHeight
                        }
                    }
                )
            }
        }

    }

    @Composable
    private fun CompactUI() {
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .height(this@BaseScreen.maxHeight)
                .verticalScroll(mainScrollState!!)
        ) {
            RunIfShowAwareHeader {
                Spacer(Modifier.height(if (showAwareHeader.value) heightAwareFaideHeader.value else 0.dp))
            }

            if (this@BaseScreen is IShowStickyHeader) {
                Spacer(Modifier.height(heightStickyHeader.value))
            }

            ComposeView(viewModel.viewState.value)
        }

    }


    override fun onPause() {
        super.onPause()
        scrollPositionBaseScreen.intValue = mainScrollState?.value ?: 0
    }
}