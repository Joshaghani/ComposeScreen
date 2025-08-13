package com.github.mohammadjoshaghani.composescreen.base.navigation

import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.RootScreen

object LifecycleOrchestrator {
    fun onAdded(screen: RootScreen<*, *, *, *>) { screen.onStart(); screen.onResume() }
    fun onRemoved(screen: RootScreen<*, *, *, *>) { screen.onPause(); screen.onDestroy() }
    fun onBecameCurrent(screen: RootScreen<*, *, *, *>) { screen.onRestart(); screen.onResume() }
}