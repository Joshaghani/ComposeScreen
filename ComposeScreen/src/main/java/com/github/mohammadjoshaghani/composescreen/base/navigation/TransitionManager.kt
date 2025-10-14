package com.github.mohammadjoshaghani.composescreen.base.navigation
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TransitionManager(
    private val scope: CoroutineScope
) {
    fun popWithAnimation(
        current: IRootScreen,
        delayMs: Long,
        after: () -> Unit
    ) {
        current.isVisibleAnimation.value = false
        scope.launch { delay(delayMs); after() }
    }
}