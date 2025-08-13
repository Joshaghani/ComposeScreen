package com.github.mohammadjoshaghani.composescreen.base.handler

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface IShowStickyHeader {

    @Composable
    fun ComposeStickyView(modifier: Modifier)

    fun getStickyForSizeScreen(): WindowWidthSizeClass? = null


}

