package com.github.mohammadjoshaghani.composescreen.commonCompose.fab

data class FabIconModel(
    val iconId: Int,
    val title: String? = null,
    val onFabPressed: () -> Unit,
)