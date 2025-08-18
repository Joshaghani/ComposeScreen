package com.github.mohammadjoshaghani.composescreen.commonCompose.navigationRail

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun NavigationIcon(
    item: NavigationItem,
    selected: Boolean,
) {
    BadgedBox(
        badge = {
            if (item.badgeCount != null) {
                Badge {
                    Text(text = item.badgeCount.toString())
                }
            } else if (item.hasNews) {
                Badge()
            }
        }
    ) {
        Icon(
            painter = painterResource(if (selected) item.selectedIcon else item.unselectedIcon),
            contentDescription = item.title,
            tint = if (selected) item.selectedColor else item.unselectedColor
        )
    }
}