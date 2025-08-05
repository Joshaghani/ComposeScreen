package com.github.mohammadjoshaghani.composescreen.commonCompose.topbar

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbarMain
import com.github.mohammadjoshaghani.composescreen.base.screen.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.ClickableIcon
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar.ShowTitleMain(scrollBehavior: TopAppBarScrollBehavior, isScrolled: Boolean) {
    val screen = Navigator.currentScreen.value

    CenterAlignedTopAppBar(
        modifier = Modifier.onGloballyPositioned { coordinates ->
            topBarHeightPx.intValue = coordinates.size.height
        },
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
        if (screen is BaseScreenLazyList) {
            if (screen.isStickyHeader) return
        }
        HorizontalDivider()
    }
}
