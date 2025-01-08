package xyz.teamgravity.predictivebackgesturedemo

import androidx.activity.BackEventCompat
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import xyz.teamgravity.predictivebackgesturedemo.ui.Route

@Composable
fun ScreenA(
    controller: NavHostController
) {
    val density = LocalDensity.current
    var textExpanded: Boolean by remember { mutableStateOf(false) }
    var backProgress: Float? by remember { mutableStateOf(null) }
    var textHeightExpanded: Dp by remember { mutableStateOf(0.dp) }
    var textHeightCollapsed: Dp by remember { mutableStateOf(0.dp) }
    val callback: OnBackPressedCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                textExpanded = false
                backProgress = null
            }

            override fun handleOnBackProgressed(backEvent: BackEventCompat) {
                super.handleOnBackProgressed(backEvent)
                backProgress = backEvent.progress
            }

            override fun handleOnBackCancelled() {
                super.handleOnBackCancelled()
                backProgress = null
            }
        }
    }

    val dispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
    DisposableEffect(
        key1 = dispatcher,
        key2 = textExpanded
    ) {
        if (textExpanded) dispatcher.addCallback(callback)

        onDispose {
            callback.remove()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.screen_a),
            fontSize = 42.sp
        )
        Button(
            onClick = {
                controller.navigate(Route.ScreenB)
            }
        ) {
            Text(
                text = stringResource(R.string.next)
            )
        }
        Text(
            text = stringResource(if (textExpanded) R.string.long_text else R.string.tap_to_expand),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .onGloballyPositioned {
                    when {
                        textExpanded && backProgress == null -> {
                            textHeightExpanded = with(density) { it.size.height.toDp() }
                        }

                        !textExpanded -> {
                            textHeightCollapsed = with(density) { it.size.height.toDp() }
                        }
                    }
                }
                .then(
                    if (backProgress == null) Modifier
                    else Modifier
                        .heightIn(min = textHeightCollapsed)
                        .height(textHeightExpanded * (1F - (backProgress ?: 0F)))
                )
                .fillMaxWidth()
                .clickable { textExpanded = !textExpanded }
                .animateContentSize()
                .padding(32.dp)
        )
    }
}