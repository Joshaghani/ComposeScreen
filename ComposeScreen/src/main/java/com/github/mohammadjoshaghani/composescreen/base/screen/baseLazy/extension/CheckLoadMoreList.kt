package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension

fun isLoadMoreList(list: MutableList<*>?): Boolean {

    if (list == null) {
        return false
    }

    if (list.isEmpty()) {
        return false
    }

    return list.size % 10 == 0
}
