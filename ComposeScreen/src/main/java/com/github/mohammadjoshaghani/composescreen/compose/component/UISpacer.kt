package com.github.mohammadjoshaghani.composescreen.compose.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UISpacer(size: Int = 16) {
    Spacer(modifier = Modifier.size(size.dp))
}
