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
import com.github.mohammadjoshaghani.composescreen.commonCompose.bottomSheet.UIBottomSheet
import com.github.mohammadjoshaghani.composescreen.commonCompose.dialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize
import kotlinx.coroutines.flow.MutableStateFlow

abstract class RootScreen<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> {

    abstract val viewModel: VM
    abstract val handler: BaseHandler<VM, Effect, Event>

    internal var isVisibleAnimation = MutableStateFlow(false)
    val animationTime = 150L

    val screenSize = mutableStateOf(ScreenSize(0.dp, 0.dp))
    private var updatedDataModel: List<Any>? = null

    var stickyState = StickyHeaderState()

    var showAwareHeader: MutableState<Boolean> =
        mutableStateOf(this is IShowScrollAwareFadingHeader)
    val heightAwareFaideHeader: MutableState<Dp> = mutableStateOf(0.dp)

    var onEventSent: (Event) -> Unit = { viewModel.setEvent(it) }
    var showAnimation: Boolean = true
        private set

    fun show(replace: Boolean = false, animation: Boolean = true) {
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
    abstract fun ShowScreenFromApp()

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
            StickyHeaderHost(
                screen = this,
                state = stickyState
            ) {
                screen.InitBaseComposeScreen(viewModel.viewState.value)
            }
        }

        ScreenSideEffects(
            state = viewModel.viewState.value,
            clearToast = { viewModel.viewState.value.toastMessage = null }
        )

        // اگر این صفحه Sticky دارد، گیت سایز را اعمال کن
        (this as? IShowStickyHeader)?.let { sticky ->
            ApplyStickyVisibilityBySize(
                screen = sticky
            ) { visible ->
                stickyState.hasStickyHeader.value = visible
                if (!visible) stickyState.stickyHeaderHeight = 0.dp
            }
        }
    }

    // API عمومی برای فاصله‌ها:
    @Composable
    fun StickySpacer() = StickySpacer(stickyState)

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
    fun MediumUI(compactUI: @Composable () -> Unit) {
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
            modifier = Modifier.Companion.fillMaxSize(),
            contentAlignment = Alignment.Companion.Center
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

    open fun onStart() {}

    open fun onResume() {}

    open fun onRestart() {
        updatedDataModel?.let { data ->
            viewModel.getResult(data)
            updatedDataModel = null
        }
    }

    private fun cleanupResources() {
        UIBottomSheet.Companion.getBottomSheet()?.hide()
        UIAlertDialog.Companion.getDialog()?.dismiss()
    }

    open fun onPause() {
        cleanupResources()
    }

    open fun onDestroy() {
        cleanupResources()
    }

    open fun onBackPressed(
        updateData: List<Any>? = null,
        backFromDialog: Boolean = false,
    ): Boolean {
        updateData?.let { setResult(it) }

        return when {
            backFromDialog -> {
                UIAlertDialog.Companion.getDialog()?.dismiss()
                Navigator.pop()
            }

            UIAlertDialog.Companion.isShow() -> {
                UIAlertDialog.Companion.getDialog()?.dismiss()
                true
            }

            else -> Navigator.pop()
        }

    }


    companion object {
        fun setResult(value: List<Any>?) {
            Navigator.previous()?.updatedDataModel = value
        }
    }
}