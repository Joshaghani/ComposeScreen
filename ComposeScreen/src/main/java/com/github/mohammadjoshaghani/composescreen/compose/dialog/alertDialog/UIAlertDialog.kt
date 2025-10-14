package com.github.mohammadjoshaghani.composescreen.compose.dialog.alertDialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UIAlertDialog(
    internal var title: String? = null,
    internal var message: String? = null,
    internal var buttonActionText: String? = null,
    internal var buttonActionDismissAfterClick: Boolean = true,
    internal var buttonActionBlock: (() -> Unit)? = null,
    internal var buttonCancelTitle: String? = null,
    internal var buttonCancelBlock: (() -> Unit)? = null,
    internal var setCanceledOnTouchOutside: Boolean = true,
    internal var primaryButtonContainerColor: Color = ApplicationConfig.config.color.primary,
    internal var primaryButtonContentColor: Color = ApplicationConfig.config.color.onPrimary,
    internal var cancelButtonContentColor: Color = ApplicationConfig.config.color.onBackground,
) : IAlertDialog, CoroutineScope {

    private val isShowDialogFlow = MutableStateFlow(true)

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun show() {
        showSampleDialogState.value = this
    }

    fun onDismissRequest(action: () -> Unit) = apply {
        launch {
            isShowDialogFlow.collect {
                if (!it) action()
            }
        }
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

    fun setButtonAction(
        text: String,
        dismissAfterClick: Boolean = true,
        block: (() -> Unit)? = null,
    ) = apply {
        this.buttonActionText = text
        this.buttonActionBlock = block
        this.buttonActionDismissAfterClick = dismissAfterClick
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
            SampleUiAlertDialog()
        }
    }


    override fun dismiss() {
        showSampleDialogState.value = null
        isShowDialogFlow.value = false
    }

    fun cancelCoroutine() {
        job.cancel()
    }

    companion object {

        private val showSampleDialogState: MutableState<IAlertDialog?> by lazy {
            mutableStateOf(null)
        }

        fun isShow(): Boolean = showSampleDialogState.value != null
        fun getDialog(): IAlertDialog? = showSampleDialogState.value
    }
}