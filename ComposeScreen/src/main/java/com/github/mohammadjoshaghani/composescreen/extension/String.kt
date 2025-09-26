package com.github.mohammadjoshaghani.composescreen.extension

import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastMessageModel
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastState

fun String.toast(state: ToastState = ToastState.ERROR): ToastMessageModel {
    return ToastMessageModel(this, state)
}
