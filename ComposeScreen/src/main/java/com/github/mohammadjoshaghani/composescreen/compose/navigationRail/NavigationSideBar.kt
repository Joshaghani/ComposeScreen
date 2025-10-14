package com.github.mohammadjoshaghani.composescreen.compose.navigationRail

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowNavigationSideBar
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen
import com.github.mohammadjoshaghani.composescreen.extension.themeClickable
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import com.github.mohammadjoshaghani.composescreen.utils.WindowSizeBus

class NavigationSideBar(val startScreen: IRootScreen) {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    fun Show() {

        val windowClass = calculateWindowSizeClass(LocalActivity.current as Activity)

        LaunchedEffect(windowClass) {
            WindowSizeBus.windowSizeClass.value = windowClass.widthSizeClass
        }
        val showNavigationRail =
            windowClass.widthSizeClass != WindowWidthSizeClass.Compact


        if (showNavigationRail) {
            var show by rememberSaveable { mutableStateOf(false) }
            LaunchedEffect(Navigator.state.current.value) {
                show = Navigator.state.current.value is IShowNavigationSideBar
            }
            if (show) UIContentSideBar()
        }
    }

    @Composable
    fun UIContentSideBar() {
        val screen = Navigator.state.current.value
        if (screen !is IShowNavigationSideBar) return

        NavigationRail(
            contentColor = ApplicationConfig.config.color.onBackground,
            containerColor = ApplicationConfig.config.color.background,
            header = {

                screen.headerIconsSideBar()
                    .forEachIndexed { index, item ->
                        Image(
                            painter = painterResource(if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.large)
                                .themeClickable {
                                    selectedItemIndex = index
                                    item.onIconClicked()
                                }
                                .height(56.dp)
                        )
                        Text(item.title)
                    }

            },
            modifier = Modifier
                .background(
                    ApplicationConfig.config.color.inverseOnSurface,
                    MaterialTheme.shapes.medium
                )
                .clip(MaterialTheme.shapes.medium)
                .padding(16.dp)
        ) {
            var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Bottom)
            ) {
                screen.actionIconsSideBar()
                    .forEachIndexed { index, item ->
                        NavigationRailItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
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


    companion object {
        var selectedItemIndex by  mutableIntStateOf(0)
    }
}
