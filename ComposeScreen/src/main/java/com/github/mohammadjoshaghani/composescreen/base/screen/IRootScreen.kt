package com.github.mohammadjoshaghani.composescreen.base.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.Dp
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.compose.bottomSheet.IBottomSheet
import com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon.ClickableIcon
import com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.compose.dialog.alertDialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.compose.dialog.base.IBaseDialog
import com.github.mohammadjoshaghani.composescreen.compose.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.compose.topbar.UITopBar
import com.github.mohammadjoshaghani.composescreen.utils.ScreenSize
import kotlinx.coroutines.flow.MutableStateFlow

interface IRootScreen {

    var isVisibleAnimation: MutableStateFlow<Boolean>

    val animationTime: Long
        get() = 150L

    var showAnimation: Boolean

    val hasStickyHeader : MutableStateFlow<Boolean>

    val stickyHeaderHeight : MutableState<Dp>

    val screenSize: MutableState<ScreenSize>

    val showAwareHeader: MutableState<Boolean>

    val heightAwareFaideHeader: MutableState<Dp>

    var result: List<Any>?

    fun show(replace: Boolean = false, animation: Boolean = true)

    fun onStart() {}

    fun onResume() {}

    fun onRestart(vararg result: Any?) {
    }

    fun setReuslt(vararg result: Any) {
        Navigator.previous()?.result = result.toList()
    }

    private fun cleanupResources() {
        UIAlertDialog.getDialog()?.dismiss()
        IBaseDialog.stack.clear()
        IBottomSheet.stack.clear()
    }

    fun onPause() {
        cleanupResources()
    }

    fun onDestroy() {
        cleanupResources()
    }

    fun onBackPressed(
        isCloseDialogAndBackScreen: Boolean = false,
    ): Boolean {

        return when {
            isCloseDialogAndBackScreen -> {
                UIAlertDialog.getDialog()?.dismiss()
                IBaseDialog.stack.clear()
                IBottomSheet.stack.clear()
                Navigator.pop()
            }

            UIAlertDialog.isShow() -> {
                UIAlertDialog.getDialog()?.dismiss()
                true
            }

            IBaseDialog.stack.isNotEmpty() -> {
                IBaseDialog.stack.clear()
                true
            }

            IBottomSheet.stack.isNotEmpty() -> {
                IBottomSheet.stack.clear()
                true
            }

            else -> Navigator.pop()
        }


    }

    @Composable
    fun ShowScreenFromApp(padding: PaddingValues)

    @Composable
    fun BottomBarView()


    fun titleTopBar(): UITopBar = UITopBar.Compose {}

    fun actionIconsTopBar(): List<IClickableIconModel> {
        return listOf()
    }

    fun iconFab(): FabIconModel? = null

    @Composable
    fun NavigationIcon() {
        Navigator.previous()?.let {
            ClickableIcon(icon = Icons.AutoMirrored.Rounded.ArrowBack) {
                Navigator.state.current.value?.onBackPressed()
            }
        }
    }

}