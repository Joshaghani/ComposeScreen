package com.github.mohammadjoshaghani.composescreen.base.screen

import SwipeToGoBackWrapper
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.github.mohammadjoshaghani.composescreen.commonCompose.bottomSheet.UIBottomSheet
import com.github.mohammadjoshaghani.composescreen.commonCompose.dialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.commonCompose.toast.ToastCreator
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


abstract class RootScreen<State : ViewState<Event>, Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<Event, State, Effect>> {

    abstract val viewModel: VM

    abstract val handler: BaseHandler<VM, Effect, Event>

    internal var isVisibleAnimation = mutableStateOf(false)
    private var updatedDataModel: List<Any>? = null

    protected var onEventSent: (Event) -> Unit = { event ->
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
    protected fun SetStateComposeScreen(screen: IScreenInitializer) {
        viewModel.launchOnScope {
            viewModel.effect.onEach { effect ->
                handler.handleEffects(effect, viewModel)
            }.collect()
        }

        when {
            viewModel.viewState.value.isLoading -> ShowLoadingIndicator()
            viewModel.viewState.value.errorScreen != null -> ShowErrorScreen()
            else -> ShowContent(screen)
        }
    }


    @Composable
    private fun ShowContent(screen: IScreenInitializer) {
        if (this is IDeactiveSwipeBackHandler) {
            SwipeToGoBackWrapper {
                screen.InitBaseComposeScreen()
            }
        } else {
            screen.InitBaseComposeScreen()
        }

        viewModel.viewState.value.toastMessage?.let { toastMessage ->
            viewModel.launchOnScope {
                ToastCreator.showToast(toastMessage)
                viewModel.viewState.value.toastMessage = null
            }
        }

        RenderDialogs()
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

    companion object {
        fun setResult(value: List<Any>?) {
            Navigator.getPreviousScreen()?.updatedDataModel = value
        }
    }
}
