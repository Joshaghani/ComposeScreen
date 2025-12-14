package com.github.mohammadjoshaghani.composescreen.utils

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable

data class Config(
    var color: ColorScheme,
    var isDarkTheme: Boolean = false,
    var isRtl: Boolean = true,
    var fabPosition: FabPosition = FabPosition.End,
    val errorScreen: @Composable (
        message: String,
        retryClick: () -> Unit,
    ) -> Unit,
    val onBack: () -> Unit = {},
)