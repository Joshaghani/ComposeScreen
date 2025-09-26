package com.github.mohammadjoshaghani.composescreen.compose.component

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.extension.clickableTheme


@Composable
fun UITextButton(
    title: String,
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    paddingHorizontal: Int = 16,
    textColor: Color = MaterialTheme.colorScheme.primary,
    leftIcon: Int? = null,
    rightIcon: Int? = null,
    clickable: () -> Unit,
) {

    Row(
        modifier = modifier
            .clipToBounds()
            .clip(MaterialTheme.shapes.medium)
            .clickableTheme(enabled = enable) {
                clickable()
            }
            .padding(horizontal = paddingHorizontal.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        leftIcon?.let {
            UIIcon(resource = it, tint = textColor)
            UISpacer(8)
        }

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = textColor.copy(alpha = if (enable) 1.0f else 0.3f),
            softWrap = false,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.wrapContentWidth().basicMarquee()
        )

        rightIcon?.let {
            UIIcon(resource = it, tint = textColor)
            UISpacer(8)
        }
    }
}

@Composable
fun UITextButtonVector(
    title: String,
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    paddingHorizontal: Int = 16,
    textColor: Color = MaterialTheme.colorScheme.primary,
    leftIconVector: ImageVector? = null,
    rightIconVector: ImageVector? = null,
    clickable: () -> Unit,
) {

    Row(
        modifier = modifier
            .clipToBounds()
            .clip(MaterialTheme.shapes.medium)
            .clickableTheme(enabled = enable) {
                clickable()
            }
            .padding(horizontal = paddingHorizontal.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        leftIconVector?.let {
            UIIcon(imageVector = it, tint = textColor)
            UISpacer(8)
        }

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = textColor.copy(alpha = if (enable) 1.0f else 0.3f),
            softWrap = false,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.wrapContentWidth().basicMarquee()
        )

        rightIconVector?.let {
            UIIcon(imageVector = it, tint = textColor)
            UISpacer(8)
        }
    }
}
