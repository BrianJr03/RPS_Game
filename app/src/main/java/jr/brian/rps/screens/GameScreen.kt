package jr.brian.rps.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jr.brian.rps.ui.theme.RPSTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import jr.brian.rps.data.RPSMove
import jr.brian.rps.composables.GameMenu
import jr.brian.rps.composables.GameOutcome
import jr.brian.rps.composables.RPSLottieAnimation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val rpsMove = remember { mutableStateOf(RPSMove.EMPTY) }
    val showOutcome = remember { mutableStateOf(false) }
    val showKonfetti = remember { mutableStateOf(false) }
    val showLottieAnimation = remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (showLottieAnimation.value) {
            RPSLottieAnimation()
        } else if (showOutcome.value) {
            GameOutcome(
                rpsMove = rpsMove.value,
                onPlayAgain = {
                    showOutcome.value = false
                    showKonfetti.value = false
                    showLottieAnimation.value = false
                }
            )
        } else {
            GameMenu(
                onMenuItemCLick = { move ->
                    rpsMove.value = move
                    scope.launch {
                        showLottieAnimation.value = true
                        delay(2000)
                        showLottieAnimation.value = false
                        showOutcome.value = true
                    }
                    scope.launch {
                        showKonfetti.value = true
                        delay(1000)
                        showKonfetti.value = false
                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun CharacterCardPreview() {
    RPSTheme {
        GameScreen()
    }
}