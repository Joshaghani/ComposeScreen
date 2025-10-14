package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.compose.bottomSheet.IBottomSheet
import com.github.mohammadjoshaghani.composescreen.compose.dialog.alertDialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.compose.dialog.base.IBaseDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderDialogs() {
    when {
        UIAlertDialog.isShow() -> {
            UIAlertDialog.getDialog()
                ?.ShowDialog()
        }

        IBaseDialog.stack.isNotEmpty() -> {
            IBaseDialog.stack.last().ShowDialog()
        }

        IBottomSheet.stack.isNotEmpty() -> {
            IBottomSheet.stack.last().ShowBottomSheet()
        }
    }
}