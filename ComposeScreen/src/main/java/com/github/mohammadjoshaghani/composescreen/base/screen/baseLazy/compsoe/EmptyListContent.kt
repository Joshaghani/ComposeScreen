package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList

@Composable
fun BaseScreenLazyList<*, *, *, *>.EmptyListContent() {
    Column(
        modifier = Modifier.Companion
            .fillMaxWidth(0.85f)
            .padding(top = 24.dp)
            .heightIn(min = 86.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Text(
            warningMessageEmptyList,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Companion.Center,
            fontWeight = FontWeight.Companion.Bold
        )
    }

}