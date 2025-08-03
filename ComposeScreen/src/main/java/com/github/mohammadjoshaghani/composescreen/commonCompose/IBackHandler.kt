package com.github.mohammadjoshaghani.composescreen.commonCompose

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.base.Navigator
import com.github.mohammadjoshaghani.composescreen.commonCompose.toast.ToastCreator
import com.github.mohammadjoshaghani.composescreen.commonCompose.toast.ToastState
import com.github.mohammadjoshaghani.composescreen.extension.toast
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.system.exitProcess
import androidx.activity.compose.BackHandler


private var doubleBackToExitPressedOnce = false

@Composable
fun UIBackHandler() {
    BackHandler {
        if (Navigator.currentScreen.value?.onBackPressed() == false) {

            if (doubleBackToExitPressedOnce) {
                exitProcess(0)
            }

            doubleBackToExitPressedOnce = true

            MainScope().launch {
                ToastCreator.showToast("برای خروج دوباره کلید بازگشت را بزنید".toast(state = ToastState.WARNING))
            }

            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackToExitPressedOnce = false
            }, 2000)

        }
    }
}