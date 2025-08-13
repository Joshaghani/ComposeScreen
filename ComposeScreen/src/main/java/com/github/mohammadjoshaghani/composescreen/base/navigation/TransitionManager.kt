package com.github.mohammadjoshaghani.composescreen.base.navigation
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen
import kotlinx.coroutines.*

class TransitionManager(
    private val scope: CoroutineScope
) {
    fun popWithAnimation(
        current: RootScreen<*, *, *, *>,
        delayMs: Long,
        after: () -> Unit
    ) {
        current.isVisibleAnimation.value = false
        scope.launch { delay(delayMs); after() }
    }
}