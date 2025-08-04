package com.github.mohammadjoshaghani.composescreen.app

import android.R.attr.maxHeight
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.bottomBarHeightPx
import com.github.mohammadjoshaghani.composescreen.commonCompose.topbar.topBarHeightPx

val screenSize = mutableStateOf(ScreenSize(0.dp, 0.dp))

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun AppLayout(content: @Composable () -> Unit) {

    BoxWithConstraints {

        val availableHeight = with(LocalDensity.current) {
            maxHeight.toPx() - topBarHeightPx.intValue - bottomBarHeightPx.value
        }

        screenSize.value.availableHeight = availableHeight.dp

        LaunchedEffect(maxWidth, maxHeight) {
            val width = maxWidth
            val height = maxHeight
            screenSize.value = ScreenSize(width, height)
        }

        content()
    }
}