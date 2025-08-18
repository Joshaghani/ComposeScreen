package com.github.mohammadjoshaghani.composescreen.commonCompose.navigationRail

import androidx.compose.ui.graphics.Color
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

data class NavigationItem(
    val title: String,
    val unselectedIcon: Int,
    val unselectedColor: Color = ApplicationConfig.config.color.surfaceVariant,
    val selectedColor: Color = ApplicationConfig.config.color.onSurfaceVariant,
    val selectedIcon: Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val onIconClicked: () -> Unit,
)