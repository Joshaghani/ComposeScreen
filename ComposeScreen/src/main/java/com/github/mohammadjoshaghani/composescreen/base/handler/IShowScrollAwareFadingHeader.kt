package com.github.mohammadjoshaghani.composescreen.base.handler

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

interface IShowScrollAwareFadingHeader {

    var showAwareHeader: MutableState<Boolean>

    val heightAwareFaideHeader: MutableState<Dp>

    @Composable
    fun UIScrollAwareFadingHeader(modifier: Modifier)

}