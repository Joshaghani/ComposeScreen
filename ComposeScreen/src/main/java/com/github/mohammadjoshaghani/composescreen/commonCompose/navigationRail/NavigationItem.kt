package com.github.mohammadjoshaghani.composescreen.commonCompose.navigationRail


data class NavigationItem(
    val title: String,
    val unselectedIcon: Int,
    val selectedIcon: Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val onIconClicked: () -> Unit,
)