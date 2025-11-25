package com.github.mohammadjoshaghani.composescreen.base.navigation

import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen
import kotlinx.coroutines.MainScope

object Navigator {

    private val backStack = BackStack<IRootScreen>()
    private val forwardStack = ArrayDeque<IRootScreen>()

    private val lifecycle = LifecycleOrchestrator
    private val transitions = TransitionManager(scope = MainScope())

    val state = NavigatorState()

    fun push(screen: IRootScreen) {
        current()?.let { lifecycle.onPause(it) }
        // هر بار صفحه جدید باز می‌کنیم، forward دیگه معنایی نداره
        forwardStack.clear()

        backStack.push(screen)
        state.set(screen)
        lifecycle.onAdded(screen)  // onStart + onResume
    }

    fun replace(screen: IRootScreen) {
        current()?.let {
            lifecycle.onRemoved(it) // onPause + onDestroy
            backStack.pop()
        }
        // replace هم مثل push، forward رو خالی کن
        forwardStack.clear()
        push(screen)
    }

    fun pop(): Boolean {
        val cur = current() ?: return false
        if (backStack.size <= 1) return false

        // Current از صحنه خارج می‌شود
        lifecycle.onRemoved(cur)           // onPause + onDestroy
        forwardStack.addFirst(cur)         // برای forward نگه می‌داریم
        backStack.pop()

        // بعد از انیمیشن خروج، صفحه جدید current می‌شود
        transitions.popWithAnimation(cur, cur.animationTime) {
            val now = current()
            state.set(now)
            now?.let { lifecycle.onBecameCurrent(it) } // onRestart + onResume
        }
        return true
    }

    fun pop(count: Int) {
        if (count <= 0) return
        // همه را به forward منتقل کنیم تا forward() کار کند
        val removed = backStack.pop(count) // ترتیب LIFO: اول current
        removed.forEach { screen ->
            lifecycle.onRemoved(screen)
            forwardStack.addFirst(screen) // current در ابتدا قرار بگیرد
        }

        val now = current()
        state.set(now)
        now?.let { lifecycle.onBecameCurrent(it) } // onRestart + onResume

    }

    fun forward(): Boolean {
        if (forwardStack.isEmpty()) return false

        val next = forwardStack.removeFirst()
        current()?.onPause()

        backStack.push(next)
        state.set(next)

        // چون قبلاً onDestroy شده، مثل ورود تازه رفتار می‌کنیم
        next.onStart()
        next.onResume()
        return true
    }

    fun clear() {
        // همه‌ی backStack را lifecycle-removed کن
        backStack.clear().forEach { lifecycle.onRemoved(it) }
        // forward هم باید پاک شود
        forwardStack.clear()
        state.set(null)
    }

    fun current() = backStack.peek()
    fun previous() = backStack.prev()
}