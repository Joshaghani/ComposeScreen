package com.github.mohammadjoshaghani.composescreen.utils


import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

object WindowSizeBus {
    val windowSizeClass = kotlinx.coroutines.flow.MutableStateFlow(WindowWidthSizeClass.Compact)
}