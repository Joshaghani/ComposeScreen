package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.BaseHandler
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IClearStackScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.ApplyStickyVisibilityBySize
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.AwareHeaderSpacer
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.ErrorShell
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.LoadingShell
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.ScreenSideEffects
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.StickyHeaderHost
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.StickySpacer
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.WithSwipeBackIfNeeded
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize
import kotlinx.coroutines.flow.MutableStateFlow

abstract class RootScreen<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> :
    IRootScreen {

    abstract val viewModel: VM
    abstract val handler: BaseHandler<VM, Effect, Event>

    override var isVisibleAnimation = MutableStateFlow(false)

    override var showAnimation: Boolean = true

    override val screenSize: MutableState<ScreenSize> = mutableStateOf(ScreenSize(0.dp, 0.dp))
    override val showAwareHeader: MutableState<Boolean> =
        mutableStateOf(this is IShowScrollAwareFadingHeader)

    override val heightAwareFaideHeader: MutableState<Dp> = mutableStateOf(0.dp)

    override val hasStickyHeader: MutableStateFlow<Boolean> =
        MutableStateFlow(this is IShowStickyHeader)

    override val stickyHeaderHeight: MutableState<Dp> = mutableStateOf(0.dp)

    var onEventSent: (Event) -> Unit = { viewModel.setEvent(it) }

    override var resultScreen: Any? = null

    override fun show(replace: Boolean, animation: Boolean) {
        this.showAnimation = animation

        if (this is IClearStackScreen) {
            Navigator.clear()
        }

        if (replace) {
            Navigator.replace(this)
        } else {
            Navigator.push(this)
        }

        viewModel.initViewModel()
    }

    @Composable
    abstract fun ComposeView(state: State)

    @Composable
    protected fun SetStateComposeScreen(screen: IScreenInitializer<State, Event>) {
        LaunchedEffect(viewModel) {
            viewModel.effect.collect { handler.handleEffects(it, viewModel) }
        }

        when {
            viewModel.viewState.value.isLoading -> LoadingShell()
            viewModel.viewState.value.errorScreen != null -> {
                val e = viewModel.viewState.value.errorScreen!!
                ErrorShell(e.message) { viewModel.setEvent(e.event) }
            }

            else -> ShowContent(screen)
        }
    }


    @Composable
    private fun ShowContent(screen: IScreenInitializer<State, Event>) {

        WithSwipeBackIfNeeded(this) {
            StickyHeaderHost {
                screen.InitBaseComposeScreen(viewModel.viewState.value)
            }
        }

        ScreenSideEffects(
            state = viewModel.viewState.value,
            clearToast = {
                viewModel.viewState.value.toastMessage = null
            }
        )

        // اگر این صفحه Sticky دارد، گیت سایز را اعمال کن
        (this as? IShowStickyHeader)?.let { sticky ->
            ApplyStickyVisibilityBySize(
                screen = sticky
            ) { visible ->
                hasStickyHeader.value = visible
                if (!visible) stickyHeaderHeight.value = 0.dp
            }
        }
    }


    @Composable
    fun ExpandedUI(compactUI: @Composable () -> Unit) {
        Row(Modifier.fillMaxSize()) {
            Column {
                StickySpacer()
                AwareHeaderSpacer(showAwareHeader.value, heightAwareFaideHeader.value)
                StartedExpandedUI()
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) { compactUI() }
            Column {
                StickySpacer()
                AwareHeaderSpacer(showAwareHeader.value, heightAwareFaideHeader.value)
                EndedExpandedUI()
            }
        }
    }


    @Composable
    open fun StartedExpandedUI() {
    }

    @Composable
    open fun EndedExpandedUI() {
    }


    @Composable
    private fun ShowLoadingIndicator() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun ShowErrorScreen() {
        val errorScreen = viewModel.viewState.value.errorScreen!!
        ApplicationConfig.config.errorScreen(errorScreen.message) {
            viewModel.setEvent(errorScreen.event)
        }
    }

    @Composable
    override fun BottomBarView() {
        BottomBarView(viewModel.viewState.value)
    }

    @Composable
    fun BottomBarView(state: State) {
    }

}