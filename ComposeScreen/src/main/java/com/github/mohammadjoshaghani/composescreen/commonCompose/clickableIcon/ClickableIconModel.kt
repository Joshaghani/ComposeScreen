package com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon

import androidx.compose.ui.graphics.vector.ImageVector

sealed interface IClickableIconModel {
    data class ClickableIconModel(
        val iconId: Int,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
    ) : IClickableIconModel

    data class ClickableIconVectorModel(
        val iconId: ImageVector,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
    ) : IClickableIconModel
}