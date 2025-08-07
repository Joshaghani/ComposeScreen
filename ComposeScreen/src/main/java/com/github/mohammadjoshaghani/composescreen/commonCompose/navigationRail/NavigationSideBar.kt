package com.github.mohammadjoshaghani.composescreen.commonCompose.navigationRail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.app.screenSize
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowNavigationSideBar
import com.github.mohammadjoshaghani.composescreen.extension.clickableTheme
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeClass

class NavigationSideBar {

    @Composable
    fun Show() {

        val windowClass = WindowSizeClass.fromWidth(screenSize.value.width)

        val showNavigationRail = windowClass != WindowSizeClass.Compact

        if (showNavigationRail) {
            var show by rememberSaveable { mutableStateOf(false) }
            LaunchedEffect(Navigator.currentScreen.value) {
                show = Navigator.currentScreen.value is IShowNavigationSideBar
            }
            if (show) UIContentSideBar()
        }
    }

    @Composable
    fun UIContentSideBar() {
        val screen = Navigator.currentScreen.value as IShowNavigationSideBar
        NavigationRail(
            contentColor = ApplicationConfig.config.color.onBackground,
            containerColor = ApplicationConfig.config.color.background,
            header = {
                var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

                screen.headerIconsSideBar()
                    .forEachIndexed { index, item ->
                        Image(
                            painter = painterResource(if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.large)
                                .clickableTheme {
                                    selectedItemIndex = index
                                    item.onIconClicked()
                                }
                                .height(56.dp)
                        )
                        Text(item.title)
                    }

            },
            modifier = Modifier
                .background(ApplicationConfig.config.color.inverseOnSurface)
                .clip(CircleShape)
                .padding(16.dp)
        ) {
            var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Bottom)
            ) {
                screen.actionIconsSideBar()
                    .forEachIndexed { index, item ->
                        NavigationRailItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                item.onIconClicked()
                            },
                            icon = {
                                NavigationIcon(
                                    item = item,
                                    selected = selectedItemIndex == index
                                )
                            },
                            label = {
                                Text(text = item.title)
                            },
                        )
                    }
            }

        }
    }
}
