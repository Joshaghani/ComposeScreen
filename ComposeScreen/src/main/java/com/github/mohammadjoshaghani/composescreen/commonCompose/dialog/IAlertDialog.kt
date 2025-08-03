package com.github.mohammadjoshaghani.composescreen.commonCompose.dialog

import androidx.compose.runtime.Composable

interface IAlertDialog {
    @Composable
    fun ShowDialog()

    fun dismiss()
}