package com.github.mohammadjoshaghani.composescreen.base

import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.compose.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastMessageModel


interface BaseHandler<VM : BaseViewModel<*, *, Effect>, Effect : ViewSideEffect, Event: ViewEvent> {
    fun handleEffects(effect: Effect, viewModel: VM)

    fun VM.updateState(
        isLoading: Boolean = false,
        toastMessage: ToastMessageModel? = null,
        errorScreen: ErrorScreenMessageModel<Event>? = null,
    )
}