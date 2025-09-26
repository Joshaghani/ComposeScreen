package com.github.mohammadjoshaghani.composescreen.compose.toast

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object ToastCreator {

    private var _toastChannel: MutableSharedFlow<ToastMessageModel> = MutableSharedFlow()
    var toastChannel = _toastChannel.asSharedFlow()

    suspend fun showToast(message: ToastMessageModel) {
        _toastChannel.emit(message)
    }
}