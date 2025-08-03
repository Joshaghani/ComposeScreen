package com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon

data class ClickableIconModel(
    val iconId: Int,
    val badgeCount: Int? = null,
    val onIconPressed: () -> Unit,
)