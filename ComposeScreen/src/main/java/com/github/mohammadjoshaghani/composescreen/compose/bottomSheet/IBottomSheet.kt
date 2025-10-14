package com.github.mohammadjoshaghani.composescreen.compose.bottomSheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf

interface IBottomSheet {

    fun show()

    @Composable
    fun ShowBottomSheet()

    companion object {
        val stack = mutableStateListOf<IBottomSheet>()
    }
}
