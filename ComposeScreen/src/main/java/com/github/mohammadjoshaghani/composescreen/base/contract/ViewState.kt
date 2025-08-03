package com.github.mohammadjoshaghani.composescreen.base.contract

import com.github.mohammadjoshaghani.composescreen.commonCompose.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.commonCompose.toast.ToastMessageModel


interface ViewState<Event : ViewEvent> {
    var errorScreen: ErrorScreenMessageModel<Event>?
    var isLoading: Boolean
    var toastMessage: ToastMessageModel?
}