import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator

@Composable
fun SwipeToGoBackWrapper(
    threshold: Float = 100f,
    content: @Composable () -> Unit,
) {
    val layoutDirection = LocalLayoutDirection.current

    var totalDrag by remember { mutableFloatStateOf(0f) }
    var showIcon by remember { mutableStateOf(false) }

    val iconOffset by animateDpAsState(
        targetValue = if (showIcon) 50.dp else (-200).dp,
        animationSpec = spring()
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(layoutDirection) {
                detectHorizontalDragGestures(
                    onDragStart = {
                        totalDrag = 0f
                        showIcon = false
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()

                        totalDrag += dragAmount
                        // در RTL، درگ از راست به چپ باید منفی باشد
                        // در LTR، درگ از چپ به راست باید مثبت باشد
                        showIcon = when (layoutDirection) {
                            LayoutDirection.Rtl -> totalDrag < 0
                            LayoutDirection.Ltr -> totalDrag > 0
                        }
                    },
                    onDragEnd = {
                        val shouldNavigateBack = when (layoutDirection) {
                            LayoutDirection.Rtl -> totalDrag < -threshold
                            LayoutDirection.Ltr -> totalDrag > threshold
                        }
                        if (shouldNavigateBack) {
                            Navigator.state.current.value?.onBackPressed()
                        }
                        totalDrag = 0f
                        showIcon = false
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        content()

        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = iconOffset)
        )
    }
}