package com.github.mohammadjoshaghani.composescreen.base.handler

import androidx.compose.runtime.Composable


interface ILazyListScreen {
    fun getItemsList(): MutableList<IIdentifiable>

    @Composable
    fun ItemUI(index: Int, item: Any)

    @Composable
    fun FooterUI() {
    }
}
