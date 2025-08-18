package com.github.mohammadjoshaghani.composescreen.commonCompose

import androidx.compose.runtime.Composable


private var doubleBackToExitPressedOnce = false

@Composable
fun UIBackHandler() {
//    BackHandler {
//        if (Navigator.currentScreen.value?.onBackPressed() == false) {
//
//            if (doubleBackToExitPressedOnce) {
//                exitProcess(0)
//            }
//
//            doubleBackToExitPressedOnce = true
//
//            MainScope().launch {
//                ToastCreator.showToast("برای خروج دوباره کلید بازگشت را بزنید".toast(state = ToastState.WARNING))
//            }
//
//            Handler(Looper.getMainLooper()).postDelayed({
//                doubleBackToExitPressedOnce = false
//            }, 2000)
//
//        }
//    }
}