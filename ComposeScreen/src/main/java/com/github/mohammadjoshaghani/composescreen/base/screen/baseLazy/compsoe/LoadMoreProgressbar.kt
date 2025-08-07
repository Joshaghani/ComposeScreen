package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadMoreProgressbar() {
    Row(
        Modifier.Companion
            .padding(32.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}