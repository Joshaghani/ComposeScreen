package com.github.mohammadjoshaghani.composescreen.base.handler

import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.IClickableIconModel

interface IShowTopbarMain {

    fun actionIconsTopBar(): List<IClickableIconModel> {
        return listOf()
    }

    fun menuIconTopBar(): IClickableIconModel?

}