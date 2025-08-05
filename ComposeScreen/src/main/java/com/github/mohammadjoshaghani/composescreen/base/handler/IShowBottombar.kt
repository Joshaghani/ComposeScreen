package com.github.mohammadjoshaghani.composescreen.base.handler

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned

val bottomBarHeightPx = mutableIntStateOf(0)

interface IShowBottombar {
    @Composable
    fun BottomBarView()
}