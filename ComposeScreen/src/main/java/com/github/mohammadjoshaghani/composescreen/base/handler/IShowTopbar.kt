package com.github.mohammadjoshaghani.composescreen.base.handler

import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.IClickableIconModel

interface IShowTopbar {

    fun titleTopBar(): String

    fun leftIconsTopBar(): List<IClickableIconModel> {
        return listOf()
    }

}


