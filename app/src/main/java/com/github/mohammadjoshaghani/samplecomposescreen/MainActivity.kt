package com.github.mohammadjoshaghani.samplecomposescreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.app.ComposeScreen
import com.github.mohammadjoshaghani.composescreen.base.BaseHandler
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbarMain
import com.github.mohammadjoshaghani.composescreen.base.screen.BaseScreen
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.ClickableIconModel
import com.github.mohammadjoshaghani.composescreen.commonCompose.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.commonCompose.toast.ToastMessageModel
import com.github.mohammadjoshaghani.composescreen.utils.Config
import com.github.mohammadjoshaghani.samplecomposescreen.ui.theme.SampleComposeScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleComposeScreenTheme {
                ComposeScreen(
                    MainScreen(),
                    config = Config(
                        appIconId = R.drawable.ic_launcher_background,
                        color = darkColorScheme(),
                        isDarkTheme = true,
                        errorScreen = { message, retryClick ->

                        }
                    )
                )
            }
        }
    }
}


class MainScreen : BaseScreen<
        MainScreenContract.State,
        MainScreenContract.Event,
        MainScreenContract.Effect,
        MainScreenViewModel
        >(), IShowTopbarMain {
    override val viewModel: MainScreenViewModel = MainScreenViewModel()
    override val handler: MainScreenHandler = MainScreenHandler()

    @Composable
    override fun ComposeView(state: MainScreenContract.State) {
    }

    override fun menuIconTopBar(): ClickableIconModel? {
        return null
    }

}

class MainScreenContract {

    sealed class Event : ViewEvent

    data class State(
        override var errorScreen: ErrorScreenMessageModel<Event>? = null,
        override var isLoading: Boolean = false,
        override var toastMessage: ToastMessageModel? = null,
    ) : ViewState<Event>

    sealed class Effect : ViewSideEffect
}


class MainScreenViewModel : BaseViewModel<
        MainScreenContract.Event,
        MainScreenContract.State,
        MainScreenContract.Effect,
        >() {
    override fun setInitialState() = MainScreenContract.State()

    override fun handleEvents(event: MainScreenContract.Event) {
    }

}

class MainScreenHandler : BaseHandler<
        MainScreenViewModel,
        MainScreenContract.Effect,
        MainScreenContract.Event,
        > {
    override fun handleEffects(
        effect: MainScreenContract.Effect,
        viewModel: MainScreenViewModel,
    ) {
    }

    override fun MainScreenViewModel.updateState(
        isLoading: Boolean,
        toastMessage: ToastMessageModel?,
        errorScreen: ErrorScreenMessageModel<MainScreenContract.Event>?,
    ) {
    }

}