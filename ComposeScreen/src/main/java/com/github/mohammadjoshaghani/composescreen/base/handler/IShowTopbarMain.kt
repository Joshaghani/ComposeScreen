package com.github.mohammadjoshaghani.composescreen.base.handler

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon.IClickableIconModel

interface IShowTopbarMain {

    fun titleTopBar(): @Composable () -> Unit {
        return {}
    }

    fun actionIconsTopBar(): List<IClickableIconModel> {
        return listOf()
    }

    fun menuIconTopBar(): IClickableIconModel? {
        return null
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun customTopbarUI(scrollBehavior: TopAppBarScrollBehavior): (@Composable () -> Unit)? {
        return null
    }
}