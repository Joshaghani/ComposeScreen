package com.github.mohammadjoshaghani.composescreen.compose.component


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun UIBorderButton(
    title: String,
    modifier: Modifier = Modifier,
    leftIconPainter: Int? = null,
    rightIconPainter: Int? = null,
    enabled: Boolean = true,
    textColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    border: BorderStroke = BorderStroke(
        1.dp, MaterialTheme.colorScheme.primary.copy(alpha = if (enabled) 1f else .5f)
    ),
    onClick: () -> Unit,
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor
        ),
        contentPadding = PaddingValues(vertical = 8.dp),
        border = border,
        modifier = modifier,
        shape = RoundedCornerShape(10.dp)
    ) {
        leftIconPainter?.let {
            UIIcon(
                resource = it,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(x = (-10).dp, y = 0.dp)
                    .size(20.dp),
                tint = textColor
            )
        }
        Text(
            text = title,
            color = if (enabled) {
                textColor
            } else {
                textColor.copy(alpha = .5f)
            },
            softWrap = false,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .wrapContentWidth()
                .offset(x = if (leftIconPainter != null) (-10).dp else 0.dp)
        )

        rightIconPainter?.let {
            UIIcon(
                resource = it,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(x = (-5).dp)
                    .size(20.dp),
                tint = textColor
            )
        }
    }
}

@Composable
fun UIBorderButtonVector(
    title: String,
    modifier: Modifier = Modifier,
    leftIconVector: ImageVector? = null,
    rightIconVector: ImageVector? = null,
    enabled: Boolean = true,
    textColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    border: BorderStroke = BorderStroke(
        1.dp, MaterialTheme.colorScheme.primary.copy(alpha = if (enabled) 1f else .5f)
    ),
    onClick: () -> Unit,
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor
        ),
        contentPadding = PaddingValues(vertical = 8.dp),
        border = border,
        modifier = modifier,
        shape = RoundedCornerShape(10.dp)
    ) {
        leftIconVector?.let {
            UISpacer(8)
            UIIcon(
                imageVector = it,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(x = (-10).dp, y = 0.dp)
                    .size(20.dp),
                tint = textColor
            )
        }
        Text(
            text = title,
            color = if (enabled) {
                textColor
            } else {
                textColor.copy(alpha = .5f)
            },
            softWrap = false,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .wrapContentWidth()
                .offset(x = if (leftIconVector != null) (-10).dp else 0.dp)
        )

        rightIconVector?.let {
            UIIcon(
                imageVector = it,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(x = (-5).dp)
                    .size(20.dp),
                tint = textColor
            )
            UISpacer(8)
        }
    }
}

