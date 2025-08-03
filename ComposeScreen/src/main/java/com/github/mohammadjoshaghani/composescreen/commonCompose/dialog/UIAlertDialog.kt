package com.github.mohammadjoshaghani.composescreen.commonCompose.dialog

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.commonCompose.dialog.compose.CustomUIAlertDialog
import com.github.mohammadjoshaghani.composescreen.commonCompose.dialog.compose.SampleUiAlertDialog
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import kotlinx.coroutines.flow.MutableStateFlow

class UIAlertDialog(
    internal var title: String? = null,
    internal var message: String? = null,
    internal var buttonActionText: String? = null,
    internal var buttonActionBlock: (() -> Unit)? = null,
    internal var buttonCancelTitle: String? = null,
    internal var buttonCancelBlock: (() -> Unit)? = null,
    internal var setCanceledOnTouchOutside: Boolean = true,
    internal var primaryButtonContainerColor: Color = ApplicationConfig.config.color.primary,
    internal var primaryButtonContentColor: Color = ApplicationConfig.config.color.onPrimary,
    internal var cancelButtonContentColor: Color = ApplicationConfig.config.color.onPrimary,
) : IAlertDialog {

    private val isShowDialogFlow = MutableStateFlow(false)
    private var sampleDialogContent: (@Composable ColumnScope.(UIAlertDialog) -> Unit)? = null

    internal var paddingCustomUi = 0

    fun show() {
        showSampleDialogState.value = this
        isShowDialogFlow.value = true
    }


    fun onDismissRequest(action: () -> Unit) = apply {
        Navigator.getCurrentScreen()?.viewModel?.launchOnScope {
            isShowDialogFlow.collect {
                if (!it) action()
            }
        }
    }

    @Composable
    fun SetCustomContent(
        padding: Int = 0,
        content: @Composable ColumnScope.(UIAlertDialog) -> Unit,
    ) = apply {
        this.sampleDialogContent = content
        this.paddingCustomUi = padding
    }

    fun setTitle(title: String) = apply { this.title = title }
    fun setMessage(message: String) = apply { this.message = message }

    fun setPrimaryContentButtonColor(color: Color) =
        apply { this.primaryButtonContentColor = color }

    fun setPrimaryButtonContainerColor(color: Color) =
        apply { this.primaryButtonContainerColor = color }

    fun setCancelButtonContentColor(color: Color) =
        apply { this.cancelButtonContentColor = color }

    fun setCanceledOnTouchOutside(value: Boolean) = apply {
        this.setCanceledOnTouchOutside = value
    }

    fun setButtonAction(text: String, block: (() -> Unit)? = null) = apply {
        this.buttonActionText = text
        this.buttonActionBlock = block
    }

    fun setButtonCancelTitle(text: String, block: (() -> Unit)? = null) = apply {
        this.buttonCancelTitle = text
        this.buttonCancelBlock = block
    }

    @Composable
    override fun ShowDialog() {
        if (!isShow()) return

        Dialog(
            properties = DialogProperties(
                dismissOnClickOutside = setCanceledOnTouchOutside,
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = { dismiss() }
        ) {
            sampleDialogContent?.let {
                CustomUIAlertDialog(it)
            } ?: SampleUiAlertDialog()
        }
    }


    override fun dismiss() {
        showSampleDialogState.value = null
        isShowDialogFlow.value = false
    }

    companion object {

        private val showSampleDialogState: MutableState<IAlertDialog?> by lazy {
            mutableStateOf(null)
        }

        fun isShow(): Boolean = showSampleDialogState.value != null
        fun getDialog(): IAlertDialog? = showSampleDialogState.value
    }
}

