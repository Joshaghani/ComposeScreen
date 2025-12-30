package com.github.mohammadjoshaghani.samplecomposescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.BaseHandler
import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IClearStackScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IIdentifiable
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowNavigationSideBar
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.compose.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.compose.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.compose.navigationRail.NavigationItem
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastMessageModel
import com.github.mohammadjoshaghani.samplecomposescreen.ui.UIBorderCard
import com.github.mohammadjoshaghani.samplecomposescreen.ui.UIRowSpaceBetween
import com.github.mohammadjoshaghani.samplecomposescreen.ui.theme.colorTheme

class MainScreen :
    BaseScreenLazyList<MainScreenContract.State, MainScreenContract.Event, MainScreenContract.Effect, MainScreenViewModel>(),
    IShowNavigationSideBar,
//    IShowScrollAwareFadingHeader,
    IShowStickyHeader,
    IClearStackScreen {

    override val viewModel: MainScreenViewModel = MainScreenViewModel()

    override val handler: MainScreenHandler = MainScreenHandler()


    @Composable
    override fun ComposeView(state: MainScreenContract.State) {
        Column(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Compose View", color = colorTheme.onBackground, modifier = Modifier.clickable {
                SecondScreen().show()
            })

            var text by remember { mutableStateOf("") }
            TextField(value = text, onValueChange = {
                text = it
                onEventSent(MainScreenContract.Event.ChangeText(it))
            })

        }

    }


    override fun actionIconsTopBar(): List<IClickableIconModel> {
        return listOf()
    }

    @Composable
    override fun StartedExpandedUI() {

        UIBorderCard(
            paddingTop = 0.dp,
            backgroundColor = colorTheme.background,
            modifier = Modifier
                .padding(16.dp)
                .width(100.dp)
        ) {
            Text("Start Items")
            Text("Start Items")
            Text("Start Items")
        }
    }

    @Composable
    override fun EndedExpandedUI() {
        Column(
            modifier = Modifier
                .background(colorTheme.background)
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
        ) {
            UIBorderCard(
                paddingTop = 0.dp,
                backgroundColor = colorTheme.background,
                modifier = Modifier
                    .padding(16.dp)
                    .width(100.dp)
            ) {
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
                Text("Ended Items")
            }
        }
    }

    override fun headerIconsSideBar(): List<NavigationItem> {
        return listOf(
            NavigationItem(
                "Items Icon",
                R.drawable.ic_launcher_foreground,
                unselectedColor = Color.Red,
                selectedColor = Color.Blue,
                R.drawable.ic_launcher_foreground,
                false, 0, {
                    SecondScreen().show()
                }),

            NavigationItem(
                "Items Icon",
                R.drawable.ic_launcher_foreground,
                unselectedColor = Color.Red,
                selectedColor = Color.Blue,
                R.drawable.ic_launcher_foreground,
                false, 0, {}),

            NavigationItem(
                "Items Icon",
                R.drawable.ic_launcher_foreground,
                unselectedColor = Color.Red,
                selectedColor = Color.Blue,
                R.drawable.ic_launcher_foreground,
                false, 0, {}),

            NavigationItem(
                "Items Icon",
                R.drawable.ic_launcher_foreground,
                unselectedColor = Color.Red,
                selectedColor = Color.Blue,
                R.drawable.ic_launcher_foreground,
                false, 0, {}),
        )
    }


//    @Composable
//    override fun UIScrollAwareFadingHeader(modifier: Modifier) {
//        UIBorderCard(
//            backgroundColor = colorTheme.background,
//            modifier = modifier.height(100.dp)
//        ) {
//            Column(
//                Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text("Hello from fade menu")
//            }
//        }
//    }

    override fun getItemsList(state: MainScreenContract.State): MutableList<IIdentifiable> {
        return mutableListOf(
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
            TestModel(),
        )
    }

    data class TestModel(
        val title: String = "Title",
    ) : IIdentifiable

    @Composable
    override fun ItemUI(state: MainScreenContract.State, index: Int, item: Any) {
        UIBorderCard(
            backgroundColor = colorTheme.background,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            UIRowSpaceBetween("$index", (item as TestModel).title)
        }
    }


    @Composable
    override fun ComposeStickyView(modifier: Modifier) {
        Text("Compose Sticky View", color = colorTheme.onBackground)
    }


    override fun iconFab(state: MainScreenContract.State): FabIconModel? {
        if (state.showFab) {
            return FabIconModel(
                iconVector = Icons.AutoMirrored.Default.ArrowBack,
                onFabPressed = {

                }
            )
        }
        return super.iconFab(state)
    }


    override fun getStickyForSizeScreen(): WindowWidthSizeClass? {
        return WindowWidthSizeClass.Compact
    }
}


class MainScreenContract {

    sealed interface Event : ViewEvent {
        data class ChangeText(var text: String) : Event
    }

    data class State(
        override var errorScreen: ErrorScreenMessageModel<Event>? = null,
        override var isLoading: Boolean = false,
        override var toastMessage: ToastMessageModel? = null,

        var showFab: Boolean = false
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
        when (event) {
            is MainScreenContract.Event.ChangeText -> {
                setState { copy(showFab = event.text.length > 5) }
            }
        }
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



