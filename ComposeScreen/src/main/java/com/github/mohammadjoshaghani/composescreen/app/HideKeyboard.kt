package com.github.mohammadjoshaghani.composescreen.app

fun hideKeyboard() {
    keyboardController?.hide()
    focusManager?.clearFocus()
}
