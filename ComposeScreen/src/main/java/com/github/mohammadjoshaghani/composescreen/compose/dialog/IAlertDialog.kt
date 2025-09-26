package com.github.mohammadjoshaghani.composescreen.compose.dialog

import androidx.compose.runtime.Composable

interface IAlertDialog {
    @Composable
    fun ShowDialog()

    fun dismiss()
}