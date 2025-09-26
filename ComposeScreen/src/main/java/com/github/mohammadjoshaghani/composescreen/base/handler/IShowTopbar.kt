package com.github.mohammadjoshaghani.composescreen.base.handler

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon.IClickableIconModel

interface IShowTopbar {

    sealed class UiTitle {
        data class TextResult(val text: String) : UiTitle()
        data class ComposableResult(val content: @Composable () -> Unit) : UiTitle()
    }

    fun titleTopBar(): UiTitle

    fun actionIconsTopBar(): List<IClickableIconModel> {
        return listOf()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun customTopbarUI(scrollBehavior: TopAppBarScrollBehavior): (@Composable () -> Unit)? {
        return null
    }

}


