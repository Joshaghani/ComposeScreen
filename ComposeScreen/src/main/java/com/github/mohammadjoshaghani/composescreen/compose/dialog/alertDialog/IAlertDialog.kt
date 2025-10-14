package com.github.mohammadjoshaghani.composescreen.compose.dialog.alertDialog

import androidx.compose.runtime.Composable

interface IAlertDialog {
    @Composable
    fun ShowDialog()

    fun dismiss()
}