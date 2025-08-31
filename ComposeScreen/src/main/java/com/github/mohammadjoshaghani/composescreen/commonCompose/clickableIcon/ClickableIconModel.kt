package com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon

import androidx.compose.ui.graphics.vector.ImageVector

sealed interface IClickableIconModel {
    data class ClickableIconModel(
        val iconId: Int,
        val title: String? = null,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
    ) : IClickableIconModel

    data class ClickableIconVectorModel(
        val iconId: ImageVector,
        val title: String? = null,
        val badgeCount: Int? = null,
        val onIconPressed: () -> Unit,
    ) : IClickableIconModel
}