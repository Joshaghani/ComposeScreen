package com.github.mohammadjoshaghani.composescreen.base.handler

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface IIdentifiable {
    @OptIn(ExperimentalUuidApi::class)
    val id: Any
        get() = Uuid.Companion.random()
}