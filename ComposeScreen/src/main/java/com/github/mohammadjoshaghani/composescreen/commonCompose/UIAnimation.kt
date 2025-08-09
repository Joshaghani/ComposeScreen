package com.github.mohammadjoshaghani.composescreen.commonCompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.mohammadjoshaghani.composescreen.base.Navigator

@Composable
fun UIAnimatedVisibility(function: @Composable () -> Unit) {
    val screen = Navigator.currentScreen.value
    screen ?: return

    if (!screen.showAnimation) {
        function()
        return
    }


    val visibleState by screen.isVisibleAnimation.collectAsState()

    AnimatedVisibility(
        visible = visibleState,
        enter = slideInHorizontally(
            animationSpec = tween(durationMillis = screen.animationTime.toInt()) // ورود آرام‌تر
        ) + fadeIn(
            animationSpec = tween(durationMillis = screen.animationTime.toInt())
        ),
        exit = slideOutHorizontally(
            animationSpec = tween(durationMillis = screen.animationTime.toInt()) // خروج آرام‌تر
        ) + fadeOut(
            animationSpec = tween(durationMillis = screen.animationTime.toInt())
        )
    ) {
        function()
    }
}