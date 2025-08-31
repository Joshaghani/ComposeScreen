package com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.commonCompose.UISpacer
import com.github.mohammadjoshaghani.composescreen.extension.clickableTheme

@Composable
fun ClickableIcon(
    icon: ImageVector,
    title: String? = null,
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
fun IconBox(
    icon: Int,
    title: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    if (title == null) {
        Box(
            modifier = modifier
                .size(56.dp)
                .clip(CircleShape)
                .clickableTheme(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = contentDescription,
                modifier = Modifier.size(24.dp),
                tint = tint
            )
        }
    } else {
        IconButtonMenu(
            title,
            icon,
            modifier,
            tint,
            contentDescription,
            onClick
        )
    }
}

@Composable
fun IconBox(
    icon: ImageVector,
    title: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {


    if (title == null) {
        Box(
            modifier = modifier
                .size(56.dp)
                .clip(CircleShape)
                .clickableTheme(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(24.dp),
                tint = tint
            )
        }
    } else {
        IconButtonMenu(
            title,
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
    icon: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface
        ),
        contentPadding = PaddingValues(vertical = 8.dp),
        border = BorderStroke(
            1.dp, MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier.wrapContentWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        UISpacer()

        Icon(
            imageVector = icon,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .offset(x = (-10).dp, y = 0.dp)
                .size(20.dp),
            contentDescription = null,
            tint = tint
        )

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            softWrap = false,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.wrapContentWidth()
        )
        UISpacer()

    }
}


@Composable
fun IconButtonMenu(
    title: String,
    icon: Int,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface
        ),
        contentPadding = PaddingValues(vertical = 8.dp),
        border = BorderStroke(
            1.dp, MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier.wrapContentWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        UISpacer()
        Icon(
            painterResource(icon),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .offset(x = (-10).dp, y = 0.dp)
                .size(20.dp),
            contentDescription = null,
            tint = tint
        )

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            softWrap = false,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.wrapContentWidth()
        )

        UISpacer()
    }
}
