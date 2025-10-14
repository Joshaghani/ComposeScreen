package com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.compose.component.UIBorderButton
import com.github.mohammadjoshaghani.composescreen.compose.component.UIBorderButtonVector
import com.github.mohammadjoshaghani.composescreen.compose.component.UITextButton
import com.github.mohammadjoshaghani.composescreen.compose.component.UITextButtonVector
import com.github.mohammadjoshaghani.composescreen.extension.themeClickable

@Composable
fun ClickableIcon(
    icon: ImageVector,
    title: String? = null,
    tooltip: String? = null,
    doesButtonHaveBorder: Boolean = true,
    badgeCount: Int? = null,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit,
) {

    when (badgeCount) {
        null -> {
            IconBox(
                icon,
                title,
                tooltip,
                doesButtonHaveBorder,
                modifier,
                tint,
                contentDescription,
                onClick
            )
        }

        0 -> {
            BadgedBox(
                badge = { Badge(Modifier.offset(y = 16.dp, x = (-16).dp)) } // فقط دایره خالی
            ) {
                IconBox(
                    icon,
                    title,
                    tooltip,
                    doesButtonHaveBorder,
                    modifier,
                    tint,
                    contentDescription,
                    onClick
                )
            }
        }

        else -> {
            BadgedBox(
                badge = {
                    Badge(Modifier.offset(y = 10.dp, x = (-10).dp)) {
                        Text("$badgeCount")
                    }
                }
            ) {
                IconBox(
                    icon,
                    title,
                    tooltip,
                    doesButtonHaveBorder,
                    modifier,
                    tint,
                    contentDescription,
                    onClick
                )
            }
        }
    }

}

@Composable
fun ClickableIcon(
    icon: Int,
    title: String? = null,
    tooltip: String? = null,
    doesButtonHaveBorder: Boolean = true,
    badgeCount: Int? = null,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {

    when (badgeCount) {
        null -> {
            IconBox(
                icon,
                title,
                tooltip,
                doesButtonHaveBorder,
                modifier,
                tint,
                contentDescription,
                onClick
            )
        }

        0 -> {
            BadgedBox(
                badge = { Badge(Modifier.offset(y = 16.dp, x = (-16).dp)) } // فقط دایره خالی
            ) {
                IconBox(
                    icon,
                    title,
                    tooltip,
                    doesButtonHaveBorder,
                    modifier,
                    tint,
                    contentDescription,
                    onClick
                )
            }
        }

        else -> {
            BadgedBox(
                badge = {
                    Badge(Modifier.padding(12.dp)) {
                        Text("$badgeCount")
                    }
                }
            ) {
                IconBox(
                    icon,
                    title,
                    tooltip,
                    doesButtonHaveBorder,
                    modifier,
                    tint,
                    contentDescription,
                    onClick
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconBox(
    icon: Int,
    title: String? = null,
    tooltip: String? = null,
    doesButtonHaveBorder: Boolean = true,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    if (title == null) {

        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = {
                tooltip?.let {
                    PlainTooltip {
                        Text(tooltip)
                    }
                }
            },
            state = rememberTooltipState(),
        ) {
            Box(
                modifier = modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .themeClickable(onClick = onClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = contentDescription,
                    modifier = Modifier.size(24.dp),
                    tint = tint
                )
            }
        }
    } else {
        IconButtonMenu(
            title,
            doesButtonHaveBorder,
            icon,
            modifier,
            tint,
            contentDescription,
            onClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconBox(
    icon: ImageVector,
    title: String? = null,
    tooltip: String? = null,
    doesButtonHaveBorder: Boolean = true,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    if (title == null) {

        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = {
                tooltip?.let {
                    PlainTooltip {
                        Text(tooltip)
                    }
                }
            },
            state = rememberTooltipState(),
        ) {
            Box(
                modifier = modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .themeClickable(onClick = onClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(24.dp),
                    tint = tint
                )
            }
        }
    } else {
        IconButtonMenu(
            title,
            doesButtonHaveBorder,
            icon,
            modifier,
            tint,
            contentDescription,
            onClick
        )
    }
}


@Composable
fun IconButtonMenu(
    title: String,
    doesButtonHaveBorder: Boolean = true,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {

    if (doesButtonHaveBorder) {
        UIBorderButtonVector(
            title = title,
            modifier = modifier,
            leftIconVector = icon,
            onClick = onClick,
            textColor = tint,
        )
    } else {
        UITextButtonVector(
            title = title,
            modifier = modifier,
            leftIconVector = icon,
            clickable = onClick,
            textColor = tint,
        )
    }

}


@Composable
fun IconButtonMenu(
    title: String,
    doesButtonHaveBorder: Boolean = true,
    icon: Int,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    if (doesButtonHaveBorder) {
        UIBorderButton(
            title = title,
            modifier = modifier,
            leftIconPainter = icon,
            onClick = onClick,
            textColor = tint,
        )
    } else {
        UITextButton(
            title = title,
            modifier = modifier,
            leftIcon = icon,
            clickable = onClick,
            textColor = tint,
        )
    }

}
