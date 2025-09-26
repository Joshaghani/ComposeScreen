package com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.mohammadjoshaghani.composescreen.app.RenderDialogs
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastCreator

@Composable
fun <StateT : ViewState<*>> ScreenSideEffects(
    state: StateT,
    clearToast: () -> Unit
) {
    val toastMessage = state.toastMessage
    LaunchedEffect(toastMessage) {
        if (toastMessage != null) {
            ToastCreator.showToast(toastMessage)
            clearToast()
        }
    }
    RenderDialogs()
}