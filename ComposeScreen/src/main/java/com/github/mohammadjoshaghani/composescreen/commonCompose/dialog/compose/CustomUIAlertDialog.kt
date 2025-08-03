package com.github.mohammadjoshaghani.composescreen.commonCompose.dialog.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.commonCompose.dialog.UIAlertDialog


@Composable
internal fun UIAlertDialog.CustomUIAlertDialog(content: @Composable ColumnScope.(UIAlertDialog) -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (setCanceledOnTouchOutside) {
                        dismiss()
                    }
                }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    shape = if (paddingCustomUi == 0) {
                        RoundedCornerShape(0.dp)
                    } else {
                        RoundedCornerShape(4.dp)
                    },
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                ) {
                    Column { content(this@CustomUIAlertDialog) }
                }
            }
        }
    }
}
