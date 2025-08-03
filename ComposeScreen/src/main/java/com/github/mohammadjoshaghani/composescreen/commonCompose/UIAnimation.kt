package com.github.mohammadjoshaghani.composescreen.commonCompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.Navigator

@Composable
fun UIAnimatedVisibility(function: @Composable () -> Unit) {

    if (Navigator.currentScreen.value?.showAnimation == false) {
        function()
        return
    }

    Column {
        AnimatedVisibility(
            visible = Navigator.currentScreen.value?.isVisibleAnimation?.value ?: false,
            enter = slideInHorizontally() + fadeIn()
        ) {
            Column {
                function()
            }
        }
    }

}