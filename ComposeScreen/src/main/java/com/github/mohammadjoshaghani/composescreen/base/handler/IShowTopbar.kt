package com.github.mohammadjoshaghani.composescreen.base.handler

import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.ClickableIconModel

interface IShowTopbar {

    fun titleTopBar(): String

    fun leftIconsTopBar(): List<ClickableIconModel> {
        return listOf()
    }

}


