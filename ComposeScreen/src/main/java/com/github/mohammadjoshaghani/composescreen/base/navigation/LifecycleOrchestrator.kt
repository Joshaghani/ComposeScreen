package com.github.mohammadjoshaghani.composescreen.base.navigation

import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen

object LifecycleOrchestrator {
    fun onAdded(screen: IRootScreen) {
        screen.onStart(); screen.onResume()
    }

    fun onRemoved(screen: IRootScreen) {
        screen.onPause(); screen.onDestroy()
    }

    fun onBecameCurrent(screen: IRootScreen) {
        (screen as? RootScreen<*, *, *, *>)?.viewModel?.initViewModel()
        screen.onRestart(screen.result)
        screen.onResume()
    }
}