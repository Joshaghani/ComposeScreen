package com.github.mohammadjoshaghani.composescreen.commonCompose.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbar
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.ClickableIcon
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar.ShowTitle(scrollBehavior: TopAppBarScrollBehavior, isScrolled: Boolean) {

    TopAppBar(
        title = {
            Text(
                (screen as IShowTopbar).titleTopBar(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        navigationIcon = {
            BackButton()
        },
        actions = {
            val screen = Navigator.currentScreen.value
            if (screen is IShowTopbar) {
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
                                onClick = icon.onIconPressed,
                                badgeCount = icon.badgeCount,
                            )
                        }
                    }
                }
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ApplicationConfig.config.color.background,
            scrolledContainerColor = ApplicationConfig.config.color.background
        )

    )

    if (isScrolled && ApplicationConfig.config.isDarkTheme) {
        if (screen is IShowStickyHeader) {
            val isShowStickyHeader by screen.isPermissionShowSticky.collectAsState()
            if (!isShowStickyHeader) {
                HorizontalDivider()
            }
        }
    }
}