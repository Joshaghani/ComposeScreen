package com.github.mohammadjoshaghani.composescreen.compose.fab

import androidx.compose.ui.graphics.vector.ImageVector


data class FabIconModel(
    val iconResource: Int? = null,
    val iconVector: ImageVector? = null,
    val title: String? = null,
    val onFabPressed: () -> Unit,
)