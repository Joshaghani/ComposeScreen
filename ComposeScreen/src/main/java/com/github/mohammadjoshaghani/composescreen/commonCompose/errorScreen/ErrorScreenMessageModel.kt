package com.github.mohammadjoshaghani.composescreen.commonCompose.errorScreen

data class ErrorScreenMessageModel<Event>(
    val message: String,
    val event: Event,
)