package com.github.mohammadjoshaghani.composescreen.compose.bottomSheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.app.ProvideLayoutDirection
import com.github.mohammadjoshaghani.composescreen.compose.bottomSheet.IBottomSheet.Companion.stack
import com.github.mohammadjoshaghani.composescreen.compose.toast.UIToastNotification
import com.github.mohammadjoshaghani.composescreen.extension.noRippleClickable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseSimpleBottomSheet : IBottomSheet, CoroutineScope {

    private val isShowDialogFlow = MutableStateFlow(true)

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    override fun show() {
        stack.add(this)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun ShowBottomSheet() {
        ModalBottomSheet(
            onDismissRequest = { dismiss() },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            ProvideLayoutDirection {
                Box(
                    modifier = Modifier
                        .noRippleClickable {
                            dismiss()
                        }
                ) {
                    Column(
                        modifier = Modifier.noRippleClickable {},
                        content = { ComposeView() })
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

    fun cancelCoroutine() {
        job.cancel()
    }
}