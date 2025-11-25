package com.github.mohammadjoshaghani.composescreen.base.navigation

import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator.current
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen

object LifecycleOrchestrator {
    fun onAdded(screen: IRootScreen) {
        screen.onStart(); screen.onResume()
    }

    fun onRemoved(screen: IRootScreen) {
        screen.onPause(); screen.onDestroy()
        (screen as? RootScreen<*, *, *, *>)?.viewModel?.clear()
    }

    fun onBecameCurrent(screen: IRootScreen) {
        (screen as? RootScreen<*, *, *, *>)?.viewModel?.initViewModel()
        screen.onRestart(screen.result)
        screen.onResume()
    }

    fun onPause(screen: IRootScreen) {
        current()?.onPause()
        (screen as? RootScreen<*, *, *, *>)?.viewModel?.clear()
    }
}