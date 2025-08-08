package com.github.mohammadjoshaghani.composescreen.base.screen

import SwipeToGoBackWrapper
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.app.RenderDialogs
import com.github.mohammadjoshaghani.composescreen.base.BaseHandler
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IClearStackScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IDeactiveSwipeBackHandler
import com.github.mohammadjoshaghani.composescreen.base.handler.IScreenInitializer
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.RunIfShowAwareHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.RunIfShowSticky
import com.github.mohammadjoshaghani.composescreen.commonCompose.bottomSheet.UIBottomSheet
import com.github.mohammadjoshaghani.composescreen.commonCompose.dialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.commonCompose.toast.ToastCreator
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize
import kotlinx.coroutines.flow.MutableStateFlow

abstract class RootScreen<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> {

    abstract val viewModel: VM

    abstract val handler: BaseHandler<VM, Effect, Event>

    internal var isVisibleAnimation = mutableStateOf(false)


    val screenSize = mutableStateOf(ScreenSize(0.dp, 0.dp))

    private var updatedDataModel: List<Any>? = null

    val windowSizeClass = MutableStateFlow(WindowWidthSizeClass.Expanded)


    var onEventSent: (Event) -> Unit = { event ->
        viewModel.setEvent(event)
    }

    var showAnimation: Boolean = true
        private set

    fun show(replace: Boolean = false, animation: Boolean = true) {
        this.showAnimation = animation

        if (this is IClearStackScreen) {
            Navigator.clear()
        }

        if (replace) {
            Navigator.back()
        }

        Navigator.add(this)
        viewModel.initViewModel()
    }

    @Composable
    abstract fun ComposeView(state: State)

    @Composable
    abstract fun ShowScreenFromApp()

    @Composable
    protected fun SetStateComposeScreen(screen: IScreenInitializer<State, Event>) {
        LaunchedEffect(viewModel) { // یک‌بار برای هر viewModel
            viewModel.effect.collect { effect ->
                handler.handleEffects(effect, viewModel)
            }
        }

        when {
            viewModel.viewState.value.isLoading -> ShowLoadingIndicator()
            viewModel.viewState.value.errorScreen != null -> ShowErrorScreen()
            else -> ShowContent(screen)
        }
    }

    @Composable
    private fun ShowContent(screen: IScreenInitializer<State, Event>) {
        if (this is IDeactiveSwipeBackHandler) {
            SwipeToGoBackWrapper {
                screen.InitBaseComposeScreen(viewModel.viewState.value)
            }
        } else {
            screen.InitBaseComposeScreen(viewModel.viewState.value)
        }

        val toastMessage = viewModel.viewState.value.toastMessage

        LaunchedEffect(toastMessage) {
            if (toastMessage != null) {
                ToastCreator.showToast(toastMessage)
                viewModel.viewState.value.toastMessage = null
            }
        }

        RenderDialogs()
        if (this is IShowStickyHeader) {
            SetStickyForSelectedSizeClass(this, windowSizeClass.value)
        }
    }

    @Composable
    private fun ShowLoadingIndicator() {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
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
        UIBottomSheet.getBottomSheet()?.hide()
        UIAlertDialog.getDialog()?.dismiss()
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
                UIAlertDialog.getDialog()?.dismiss()
                Navigator.back()
            }

            UIAlertDialog.isShow() -> {
                UIAlertDialog.getDialog()?.dismiss()
                true
            }

            else -> Navigator.back()
        }

    }


    @Composable
    fun ExpandedUI(compactUI: @Composable () -> Unit) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column {

                RunIfShowSticky {
                    Spacer(Modifier.height(heightStickyHeader.value))
                }

                RunIfShowAwareHeader {
                    Spacer(Modifier.height(if (showAwareHeader.value) heightAwareFaideHeader.value else 0.dp))
                }

                StartedExpandedUI()
            }
            Column(Modifier.fillMaxHeight().weight(1f)) {
                compactUI()
            }
            Column {
                RunIfShowSticky {
                    Spacer(Modifier.height(heightStickyHeader.value))
                }

                RunIfShowAwareHeader {
                    Spacer(Modifier.height(if (showAwareHeader.value) heightAwareFaideHeader.value else 0.dp))
                }
                EndedExpandedUI()
            }
        }
    }

    @Composable
    fun MediumUI(compactUI: @Composable () -> Unit) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column {
                RunIfShowSticky {
                    Spacer(Modifier.height(heightStickyHeader.value))
                }

                RunIfShowAwareHeader {
                    Spacer(Modifier.height(if (showAwareHeader.value) heightAwareFaideHeader.value else 0.dp))
                }

                StartedExpandedUI()
            }
            Column(Modifier.fillMaxHeight().weight(1f)) {
                compactUI()
            }
        }
    }

    /**
     * این کامپوز تنها زمانی فراخوانی می‌شود که صفحه در حالت **گسترده (Expanded)** باشد.
     *
     * می‌توانید برای نمایش محتوای دلخواه در سمت **شروع** (مثلاً چپ) در حالت دسکتاپ، این تابع را override کنید.
     */
    @Composable
    open fun StartedExpandedUI() {
    }

    /**
     * این کامپوز تنها زمانی فراخوانی می‌شود که صفحه در حالت **گسترده (Expanded)** باشد.
     *
     * می‌توانید برای نمایش محتوای دلخواه در سمت **پایان** (مثلاً راست) در حالت دسکتاپ، این تابع را override کنید.
     */
    @Composable
    open fun EndedExpandedUI() {
    }


    companion object {
        fun setResult(value: List<Any>?) {
            Navigator.getPreviousScreen()?.updatedDataModel = value
        }
    }
}
