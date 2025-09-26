package com.github.mohammadjoshaghani.composescreen.compose.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import com.github.mohammadjoshaghani.composescreen.app.screenSize
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbar
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.compose.UISmartMarqueeText
import com.github.mohammadjoshaghani.composescreen.compose.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon.ClickableIcon
import com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTitle(scrollBehavior: TopAppBarScrollBehavior, isScrolled: Boolean) {
    val screen = Navigator.state.current.value

    if (screen is IShowTopbar) {
        if (screen.customTopbarUI(scrollBehavior) != null) {
            screen.customTopbarUI(scrollBehavior)!!.invoke()
        } else {
            TopAppBar(
                title = {
                    when (val title = screen.titleTopBar()) {
                        is IShowTopbar.UiTitle.ComposableResult -> title.content.invoke()
                        is IShowTopbar.UiTitle.TextResult -> {
                            UISmartMarqueeText(
                                title.text,
                                textStyle = MaterialTheme.typography.titleMedium,
                                width = screenSize.value.width,
                                contentAlignment = Alignment.CenterStart
                            )
                        }
                    }
                },
                navigationIcon = {
                    Navigator.previous()?.let {
                        ClickableIcon(icon = Icons.AutoMirrored.Rounded.ArrowBack) {
                            Navigator.state.current.value?.onBackPressed()
                        }
                    }
                },
                actions = {
                    screen.actionIconsTopBar().forEach { icon ->
                        when (icon) {
                            is IClickableIconModel.ClickableIconModel -> {
                                ClickableIcon(
                                    icon.iconId,
                                    title = icon.title,
                                    doesButtonHaveBorder = icon.doesButtonHaveBorder,
                                    badgeCount = icon.badgeCount,
                                    onClick = icon.onIconPressed,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                            is IClickableIconModel.ClickableIconVectorModel -> {
                                ClickableIcon(
                                    icon.iconId,
                                    title = icon.title,
                                    doesButtonHaveBorder = icon.doesButtonHaveBorder,
                                    onClick = icon.onIconPressed,
                                    badgeCount = icon.badgeCount,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }

                    val last = screen.actionIconsTopBar().lastOrNull()

                    if (last is IClickableIconModel.ClickableIconModel) {
                        if (last.title != null && last.doesButtonHaveBorder) {
                            UISpacer()
                        }
                    } else if (last is IClickableIconModel.ClickableIconVectorModel) {
                        if (last.title != null && last.doesButtonHaveBorder) {
                            UISpacer()
                        }
                    }


                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ApplicationConfig.config.color.background,
                    scrolledContainerColor = ApplicationConfig.config.color.background
                )

            )
        }
    }

    if (isScrolled && ApplicationConfig.config.isDarkTheme) {
        if (screen is IShowStickyHeader) {
            if (!screen.stickyState.hasStickyHeader.collectAsState().value) {
                HorizontalDivider()
            }
        } else {
            HorizontalDivider()
        }
    }
}