package jr.brian.rps.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import jr.brian.rps.util.Presets
import jr.brian.rps.R
import jr.brian.rps.data.RPSMove
import jr.brian.rps.data.rpsMoves
import jr.brian.rps.util.generateOutcome
import jr.brian.rps.util.getOutcomeTextColor
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.compose.OnParticleSystemUpdateListener
import nl.dionsegijn.konfetti.core.PartySystem

@Composable
fun Konfetti() {
    KonfettiView(
        parties = Presets.explode(),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        updateListener =
        object : OnParticleSystemUpdateListener {
            override fun onParticleSystemEnded(
                system: PartySystem,
                activeSystems: Int,
            ) {

            }
        },
    )
}

@Composable
fun RPSCard(
    rpsMove: RPSMove,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.Green
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(15.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(rpsMove.drawable),
                    modifier = imageModifier,
                    contentDescription = "RPS Image",
                )
                Text(
                    text = rpsMove.moveName,
                    color = Color.Black,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(10.dp),
                )
            }
        }
    }
}

@Composable
fun RPSLottieAnimation(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.rps)
    )
    LottieAnimation(
        composition = composition,
        modifier = modifier
    )
}

@Composable
fun GameOutcome(
    rpsMove: RPSMove,
    onPlayAgain: () -> Unit
) {
    val cpuMove = rpsMoves.random()
    val outcome = generateOutcome(
        rpsMove.moveName,
        cpuMove.moveName
    )
    Konfetti()
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "${rpsMove.moveName} (You) vs ${cpuMove.moveName} (CPU)",
            color = getOutcomeTextColor(outcome),
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(15.dp))
        Text(
            text = outcome,
            color = getOutcomeTextColor(outcome),
            style = TextStyle(fontSize = 75.sp)
        )
        Spacer(Modifier.height(15.dp))
        Button(
            onClick = onPlayAgain,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text("Play Again", color = Color.Black)
        }
    }
    Konfetti()
}

@Composable
fun GameMenu(onMenuItemCLick: (RPSMove) -> Unit) {
    Text(
        text = "Who ya going with?",
        style = TextStyle(fontSize = 30.sp)
    )
    rpsMoves.onEach {
        RPSCard(
            rpsMove = it,
            modifier = Modifier.clickable {
                onMenuItemCLick(it)
            },
            imageModifier = Modifier.size(80.dp)
        )
    }
}