package com.github.mohammadjoshaghani.composescreen.commonCompose.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbarMain
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.ClickableIcon
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar.ShowTitleMain(scrollBehavior: TopAppBarScrollBehavior, isScrolled: Boolean) {

    CenterAlignedTopAppBar(
        title = {
            Image(
                modifier = Modifier.size(56.dp),
                painter = painterResource(ApplicationConfig.config.appIconId),
                contentDescription = null
            )
        },
        navigationIcon = {
            if (screen is IShowTopbarMain) {
                screen.menuIconTopBar()?.let { icon ->
                    when (icon) {
                        is IClickableIconModel.ClickableIconModel -> ClickableIcon(
                            icon.iconId,
                            badgeCount = icon.badgeCount,
                            onClick = icon.onIconPressed
                        )

                        is IClickableIconModel.ClickableIconVectorModel -> {
                            ClickableIcon(
                                icon.iconId,
                                badgeCount = icon.badgeCount,
                                onClick = icon.onIconPressed
                            )
                        }
                    }
                }
            }
        },
        actions = {
            if (screen is IShowTopbarMain) {
                screen.actionIconsTopBar().forEach { icon ->
                    when (icon) {
                        is IClickableIconModel.ClickableIconModel -> ClickableIcon(
                            icon.iconId,
                            badgeCount = icon.badgeCount,
                            onClick = icon.onIconPressed
                        )

                        is IClickableIconModel.ClickableIconVectorModel -> {
                            ClickableIcon(
                                icon.iconId,
                                badgeCount = icon.badgeCount,
                                onClick = icon.onIconPressed
                            )
                        }
                    }
                }
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            scrolledContainerColor = ApplicationConfig.config.color.background,
            containerColor = ApplicationConfig.config.color.background,
        )

    )

    if (isScrolled && ApplicationConfig.config.isDarkTheme) {
        if (screen is IShowStickyHeader) {
            val isShowStickyHeader by screen.isPermissionShowSticky.collectAsState()
            if (!isShowStickyHeader) {
                HorizontalDivider()
            }
        } else {
            HorizontalDivider()
        }
    }
}