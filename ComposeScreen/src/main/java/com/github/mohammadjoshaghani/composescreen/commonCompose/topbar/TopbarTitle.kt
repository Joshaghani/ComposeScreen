package com.github.mohammadjoshaghani.composescreen.commonCompose.topbar

import android.R.attr.type
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbar
import com.github.mohammadjoshaghani.composescreen.base.screen.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.ClickableIcon
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar.ShowTitle(scrollBehavior: TopAppBarScrollBehavior, isScrolled: Boolean) {

    TopAppBar(
        modifier = Modifier.onGloballyPositioned { coordinates ->
            topBarHeightPx.intValue = coordinates.size.height
        },
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
                screen.leftIconsTopBar().forEach { icon ->
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
            containerColor = ApplicationConfig.config.color.background,
            scrolledContainerColor = ApplicationConfig.config.color.background
        )

    )

    if (isScrolled && ApplicationConfig.config.isDarkTheme) {
        if (screen is BaseScreenLazyList) {
            if (screen.isStickyHeader) return
        }
        HorizontalDivider()
    }
}