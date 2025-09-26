package com.github.mohammadjoshaghani.composescreen.compose.errorScreen

data class ErrorScreenMessageModel<Event>(
    val message: String,
    val event: Event,
)