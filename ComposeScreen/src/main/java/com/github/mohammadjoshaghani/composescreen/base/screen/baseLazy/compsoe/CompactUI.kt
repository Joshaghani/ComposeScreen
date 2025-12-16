package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList


@Composable
fun <State : ViewState<Event>, Event : ViewEvent> BaseScreenLazyList<State, *, *, *>.CompactUI(
    state: State,
) {

    var showHeaderAware by remember { mutableStateOf(true) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < 0) {
                    // اسکرول به پایین => هاید کن
                    if (showHeaderAware) {
                        showHeaderAware = false
//                        println("⬇️ اسکرول به پایین - مخفی کردن هدر")
                        scrollEvents.value = false
                    }
                } else if (available.y > 0) {
                    // اسکرول به بالا => نشون بده
                    if (!showHeaderAware) {
                        showHeaderAware = true
//                        println("⬆️ اسکرول به بالا - نمایش هدر")
                        scrollEvents.value = true
                    }
                }
                return Offset.Zero
            }
        }
    }

    withGridItems()?.let { gridItems ->
        UILazyVerticalGrid(
            gridItems,
            state,
            modifier = Modifier
                .nestedScroll(nestedScrollConnection)
                .fillMaxSize()
        )
    } ?: run {
        UILazyColumn(
            state,
            modifier = Modifier
                .nestedScroll(nestedScrollConnection)
                .fillMaxSize()
        )
    }

    Spacer(modifier = Modifier.padding(bottom = padding.calculateBottomPadding()))

}