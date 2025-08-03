package com.github.mohammadjoshaghani.composescreen.commonCompose.toast

data class ToastMessageModel (
    val message: String,
    val state: ToastState = ToastState.ERROR
)