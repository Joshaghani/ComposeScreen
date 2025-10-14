package com.github.mohammadjoshaghani.composescreen.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig

fun Modifier.themeClickable(
    color: Color = ApplicationConfig.config.color.primary,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier {
    return clickable(
        interactionSource = MutableInteractionSource(),
        indication = ripple(color = color),
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick
    )

}

@Composable
fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier {
    return clickable(
        interactionSource = null,
        indication = null,
        enabled,
        onClickLabel,
        role,
        onClick
    )
}

