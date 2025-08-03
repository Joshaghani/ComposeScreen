package com.github.mohammadjoshaghani.composescreen.base.handler

import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.ClickableIconModel

interface IShowTopbarMain {

    fun leftIconsTopBar(): List<ClickableIconModel> {
        return listOf()
    }

    fun menuIconTopBar(): ClickableIconModel?

}