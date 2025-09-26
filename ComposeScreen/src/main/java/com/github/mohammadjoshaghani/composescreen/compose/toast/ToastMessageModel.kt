package com.github.mohammadjoshaghani.composescreen.compose.toast

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

data class ToastMessageModel @OptIn(ExperimentalTime::class) constructor(
    val message: String,
    val state: ToastState = ToastState.ERROR,
    val id: Long = Clock.System.now().epochSeconds,
)