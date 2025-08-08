package com.github.mohammadjoshaghani.samplecomposescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.handler.IClearStackScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IIdentifiable
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowNavigationSideBar
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbarMain
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.commonCompose.navigationRail.NavigationItem
import com.github.mohammadjoshaghani.samplecomposescreen.ui.UIBorderCard
import com.github.mohammadjoshaghani.samplecomposescreen.ui.UIRowSpaceBetween
import com.github.mohammadjoshaghani.samplecomposescreen.ui.theme.colorTheme
import kotlinx.coroutines.flow.MutableStateFlow

class MainScreen :
    BaseScreenLazyList<MainScreenContract.State, MainScreenContract.Event, MainScreenContract.Effect, MainScreenViewModel>(),
    IShowNavigationSideBar,
    IShowTopbarMain,
    IShowScrollAwareFadingHeader,
    IShowStickyHeader,
    IClearStackScreen {

    override val viewModel: MainScreenViewModel = MainScreenViewModel()

    override val handler: MainScreenHandler = MainScreenHandler()

    override val isPermissionShowSticky: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val heightStickyHeader: MutableState<Dp> = mutableStateOf(56.dp)

    override var showAwareHeader = mutableStateOf(true)
    override val heightAwareFaideHeader: MutableState<Dp> = mutableStateOf(0.dp)


    @Composable
    override fun ComposeView(state: MainScreenContract.State) {
        Column(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Compose View", color = colorTheme.onBackground)
        }

    }

    override fun menuIconTopBar(): IClickableIconModel? {
        return null
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
            modifier = Modifier.background(colorTheme.background)
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
                "مدیریت فروشگاه",
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                false, 0, {}),

            NavigationItem(
                "انبار داری",
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                false, 0, {}),

            NavigationItem(
                "نظرات",
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                false, 0, {}),

            NavigationItem(
                "تیکت ها",
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                false, 0, {}),

            NavigationItem(
                "مشتریان",
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                false, 0, {}),
            NavigationItem(
                "سفارشات",
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                false, 0, {}),
            NavigationItem(
                "انواع ارسال",
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                com.github.mohammadjoshaghani.composescreen.R.drawable.failed_internet,
                false, 0, {}),

            )
    }


    @Composable
    override fun UIScrollAwareFadingHeader(modifier: Modifier) {
        UIBorderCard(
            backgroundColor = colorTheme.background,
            modifier = modifier.height(100.dp)
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Hello from fade menu")
            }
        }
    }

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
    override fun ItemUI(index: Int, item: Any) {
        UIBorderCard(
            backgroundColor = colorTheme.background,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            UIRowSpaceBetween("$index", (item as TestModel).title)
        }
    }


    @Composable
    override fun ComposeStickyView() {
        Column(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Compose Sticky View", color = colorTheme.onBackground)
        }
    }

    override fun getStickyForSizeScreen(): WindowWidthSizeClass? {
        return WindowWidthSizeClass.Medium
    }

}




