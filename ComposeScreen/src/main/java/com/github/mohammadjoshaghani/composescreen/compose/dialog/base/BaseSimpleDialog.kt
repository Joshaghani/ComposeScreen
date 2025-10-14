package com.github.mohammadjoshaghani.composescreen.compose.dialog.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.mohammadjoshaghani.composescreen.app.ProvideLayoutDirection
import com.github.mohammadjoshaghani.composescreen.compose.component.UIBorderCard
import com.github.mohammadjoshaghani.composescreen.compose.dialog.base.IBaseDialog.Companion.stack
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastCreator
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastMessageModel
import com.github.mohammadjoshaghani.composescreen.compose.toast.UIToastNotification
import com.github.mohammadjoshaghani.composescreen.extension.noRippleClickable
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseSimpleDialog : IBaseDialog, CoroutineScope {

    private val isShowDialogFlow = MutableStateFlow(true)

    private var setCanceledOnTouchOutside: Boolean = true

    private var job: Job = Job()

    open var modifier: Modifier = Modifier
    open var backgroundColor: Color = ApplicationConfig.config.color.surface
    open var borderColor: Color = ApplicationConfig.config.color.outlineVariant
    open var elevation: Dp = 0.dp
    open var paddingValue: Dp = 16.dp
    open var borderWidth: Dp = 1.dp
    open var shape: CornerBasedShape = RoundedCornerShape(16.dp)
    open var verticalArrangement: Arrangement.Vertical = Arrangement.Center
    open var horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    fun setCanceledOnTouchOutside(value: Boolean) = apply {
        setCanceledOnTouchOutside = value
    }

    override fun show() {
        stack.add(this)
    }

    @Composable
    override fun ShowDialog() {
        Dialog(
            properties = DialogProperties(
                dismissOnClickOutside = setCanceledOnTouchOutside,
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = { dismiss() }
        ) {
            ProvideLayoutDirection {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable {
                            if (setCanceledOnTouchOutside) {
                                dismiss()
                            }
                        },
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Column(modifier = Modifier.noRippleClickable {}) {
                            UIBorderCard(
                                modifier = modifier,
                                backgroundColor = backgroundColor,
                                borderColor = borderColor,
                                elevation = elevation,
                                paddingValue = paddingValue,
                                borderWidth = borderWidth,
                                shape = shape,
                                verticalArrangement = verticalArrangement,
                                horizontalAlignment = horizontalAlignment,
                            ) {
                                ComposeView()
                            }
                        }

                    }
                    UIToastNotification()
                }

            }
        }
    }

    @Composable
    abstract fun ComposeView()

    fun dismiss() {
        stack.remove(this)
        isShowDialogFlow.value = false
    }


    fun onDismissRequest(action: () -> Unit) = apply {
        launch {
            isShowDialogFlow.collect {
                if (!it) action()
            }
        }
    }

    fun showToast(message: ToastMessageModel) {
        launch {
            ToastCreator.showToast(message)
        }
    }

    fun cancelCoroutine() {
        job.cancel()
    }

}