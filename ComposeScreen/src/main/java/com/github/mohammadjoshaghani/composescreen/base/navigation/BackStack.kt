package com.github.mohammadjoshaghani.composescreen.base.navigation

class BackStack<T> {
    private val stack = ArrayDeque<T>()
    fun push(item: T) { stack.addLast(item) }
    fun pop(): T? = if (stack.isEmpty()) null else stack.removeLast()
    fun pop(count: Int): List<T> = (0 until count).mapNotNull { pop() }
    fun peek(): T? = stack.lastOrNull()
    fun prev(): T? = if (stack.size >= 2) stack.elementAt(stack.size - 2) else null
    fun clear(): List<T> = buildList {
        while (true) {
            val e = pop() ?: break
            add(e)               // همه‌ی آیتم‌های حذف‌شده را برمی‌گرداند
        }
    }    val size get() = stack.size
}