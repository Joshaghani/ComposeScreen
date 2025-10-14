package com.github.mohammadjoshaghani.composescreen.compose.dialog.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf

internal interface IBaseDialog {

    fun show()

    @Composable
    fun ShowDialog()

    companion object {
        val stack = mutableStateListOf<IBaseDialog>()
    }
}