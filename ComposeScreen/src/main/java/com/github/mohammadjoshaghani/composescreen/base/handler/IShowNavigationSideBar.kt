package com.github.mohammadjoshaghani.composescreen.base.handler

import com.github.mohammadjoshaghani.composescreen.commonCompose.navigationRail.NavigationItem

interface IShowNavigationSideBar {

    fun actionIconsSideBar(): List<NavigationItem> {
        return listOf()
    }

    fun headerIconsSideBar(): List<NavigationItem> {
        return listOf()
    }

}