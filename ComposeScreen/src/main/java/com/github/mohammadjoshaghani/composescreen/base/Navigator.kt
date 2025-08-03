package com.github.mohammadjoshaghani.composescreen.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.github.mohammadjoshaghani.composescreen.base.screen.RootScreen

object Navigator {

    // region: Properties
    private val stack = mutableListOf<RootScreen<*, *, *, *>>()
    val currentScreen: MutableState<RootScreen<*, *, *, *>?> = mutableStateOf(stack.lastOrNull())
    // endregion

    // region: Public API - Navigation
    fun add(screen: RootScreen<*, *, *, *>) {
        getCurrentScreen()?.apply {
            onPause()
        }
        stack.add(screen)
        currentScreen.value = screen
        screen.run {
            onStart()
            onResume()
        }
    }

    fun clear() {
        stack.reversed().forEach {
            it.pauseAndDestroy()
        }
        stack.clear()
    }

    fun back(): Boolean {
        if (stack.size <= 1) return false
        getCurrentScreen()?.pauseAndDestroy()
        stack.removeLastOrNull()
        updateCurrentScreen()
        return true
    }

    fun back(count: Int) {
        for (i in (count - 1) downTo 0) {
            getCurrentScreen()?.pauseAndDestroy()
            stack.removeLastOrNull()
        }
        updateCurrentScreen()
    }

    // endregion

    // region: Accessors
    fun getCurrentScreen(): RootScreen<*, *, *, *>? = stack.lastOrNull()

    fun getPreviousScreen(): RootScreen<*, *, *, *>? = stack.getOrNull(
        stack.lastIndex - 1
    )
    // endregion

    // region: Lifecycle Helpers
    private fun updateCurrentScreen() {
        currentScreen.value = stack.lastOrNull()
        currentScreen.value?.restartAndResume()
    }

    private fun RootScreen<*, *, *, *>.pauseAndDestroy() {
        onPause()
        onDestroy()
    }

    private fun RootScreen<*, *, *, *>.restartAndResume() {
        onRestart()
        onResume()
    }
    // endregion


}