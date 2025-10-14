package com.github.mohammadjoshaghani.composescreen.compose.topbar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.app.screenSize
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.RunIfShowStickyBoolean
import com.github.mohammadjoshaghani.composescreen.compose.UIAnimatedVisibility
import com.github.mohammadjoshaghani.composescreen.compose.UISmartMarqueeText
import com.github.mohammadjoshaghani.composescreen.compose.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon.ClickableIcon
import com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.compose.topbar.TopBar.Companion.isLifted
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTitle() {
    val screen = Navigator.state.current.value ?: return

    var isShowStickyHeader by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(
        targetValue = if (isLifted.value) 6.dp else 0.dp,
        label = "appbar-elevation"
    )

    screen.RunIfShowStickyBoolean {
        isShowStickyHeader = it
    }


    UIAnimatedVisibility {
        Surface(
            shadowElevation = if (isShowStickyHeader) 0.dp else elevation,
        ) {
            Column {

                TopAppBar(
                    title = {
                        when (val title = screen.titleTopBar()) {
                            is UITopBar.Compose -> title.compose.invoke()
                            is UITopBar.Text -> {
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
                        screen.NavigationIcon()
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
                                        tint = icon.tint
                                    )
                                }

                                is IClickableIconModel.ClickableIconVectorModel -> {
                                    ClickableIcon(
                                        icon.iconId,
                                        title = icon.title,
                                        doesButtonHaveBorder = icon.doesButtonHaveBorder,
                                        onClick = icon.onIconPressed,
                                        badgeCount = icon.badgeCount,
                                        tint = icon.tint
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
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = ApplicationConfig.config.color.background,
                        scrolledContainerColor = ApplicationConfig.config.color.background
                    )

                )

                if (ApplicationConfig.config.isDarkTheme && !isShowStickyHeader && isLifted.value) {
                    HorizontalDivider()
                }
            }
        }
    }

}