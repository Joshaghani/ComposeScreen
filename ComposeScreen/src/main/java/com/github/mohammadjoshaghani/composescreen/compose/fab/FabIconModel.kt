package com.github.mohammadjoshaghani.composescreen.compose.fab


data class FabIconModel(
    val iconResource: Int? = null,
    val iconVector: Int? = null,
    val title: String? = null,
    val onFabPressed: () -> Unit,
)