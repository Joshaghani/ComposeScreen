package com.github.mohammadjoshaghani.samplecomposescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbar
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.BaseScreen
import com.github.mohammadjoshaghani.samplecomposescreen.ui.theme.colorTheme

class SecondScreen :
    BaseScreen<
            MainScreenContract.State,
            MainScreenContract.Event,
            MainScreenContract.Effect,
            MainScreenViewModel>(),
    IShowTopbar {

    override val viewModel: MainScreenViewModel = MainScreenViewModel()

    override val handler: MainScreenHandler = MainScreenHandler()

    @Composable
    override fun ComposeView(state: MainScreenContract.State) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(50) {
                Text("Compose View Second", color = colorTheme.onBackground)
            }
        }

    }

    override fun titleTopBar(): String {
        return "Second"
    }


}




