package com.github.mohammadjoshaghani.composescreen.commonCompose.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow

class UIBottomSheet : IBottomSheet {

    private var sampleBottomSheetContent: (@Composable ColumnScope.() -> Unit)? = null
    private val isShowDialogFlow = MutableStateFlow(false)


    @Composable
    fun SetCustomContent(content: @Composable ColumnScope.() -> Unit) = apply {
        sampleBottomSheetContent = content
    }

    @Composable
    override fun ShowBottomSheet() {
        if (!isShow()) return
        if (sampleBottomSheetContent == null) Exception("You must call the SetCustomContent function before calling show().")
        Column(content = sampleBottomSheetContent!!)
    }

    fun show() {
        showSampleBottomSheet.value = this
        isShowDialogFlow.value = true
    }

    override fun hide() {
        isShowDialogFlow.value = false
        showSampleBottomSheet.value = null
    }

    fun onDismissRequest(dismiss: () -> Unit) = apply {
        Navigator.state.current.value!!.viewModel.launchOnScope {
            isShowDialogFlow.collect {
                if (it.not())
                    dismiss()
            }
        }
    }


    companion object {
        private val showSampleBottomSheet: MutableState<IBottomSheet?> by lazy {
            mutableStateOf(null)
        }

        fun isShow() = showSampleBottomSheet.value != null
        fun getBottomSheet() = showSampleBottomSheet.value

    }
}