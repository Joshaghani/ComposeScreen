package com.github.mohammadjoshaghani.composescreen.utils


import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import kotlinx.coroutines.flow.MutableStateFlow

object WindowSizeBus {
    val windowSizeClass = MutableStateFlow(WindowWidthSizeClass.Compact)
}