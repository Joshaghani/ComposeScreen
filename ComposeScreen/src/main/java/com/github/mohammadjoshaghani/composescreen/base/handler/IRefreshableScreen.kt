package com.github.mohammadjoshaghani.composescreen.base.handler

import androidx.compose.runtime.MutableState

interface IRefreshableScreen {
    fun refreshEvent()
    var isRefreshing: MutableState<Boolean>
}