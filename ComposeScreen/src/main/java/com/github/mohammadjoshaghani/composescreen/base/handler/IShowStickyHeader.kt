package com.github.mohammadjoshaghani.composescreen.base.handler

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.Dp
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen
import kotlinx.coroutines.flow.MutableStateFlow

interface IShowStickyHeader {

    val isPermissionShowSticky: MutableStateFlow<Boolean>

    val heightStickyHeader: MutableState<Dp>

    @Composable
    fun ComposeStickyView()

    fun getStickyForSizeScreen(): WindowWidthSizeClass? = null

    @Composable
    fun SetStickyForSelectedSizeClass(
        screen: RootScreen<*, *, *, *>,
        selectedScreen: WindowWidthSizeClass?,
    ) {
        val stickyHeaderState = getStickyForSizeScreen()
        LaunchedEffect(screen.windowSizeClass.value) {
            screen.windowSizeClass.collect {
                isPermissionShowSticky.emit(stickyHeaderState == null || it == stickyHeaderState)
            }
        }

    }

}

