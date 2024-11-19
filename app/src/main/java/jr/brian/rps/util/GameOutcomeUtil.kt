package jr.brian.rps.util

import androidx.compose.ui.graphics.Color
import jr.brian.rps.data.RPSMessages
import jr.brian.rps.data.rpsMoves

fun getOutcomeTextColor(outcome: String): Color {
    return when (outcome) {
        RPSMessages.TIE_MESSAGE -> Color.Gray
        RPSMessages.USER_WIN_MESSAGE -> Color.Green
        else -> Color.Red
    }
}

fun generateOutcome(userMove: String, cpuMove: String): String {
    val rock = rpsMoves[0].moveName
    val paper = rpsMoves[1].moveName
    val scissors = rpsMoves[2].moveName
    return when {
        userMove == cpuMove -> RPSMessages.TIE_MESSAGE
        userMove == rock && cpuMove == scissors -> RPSMessages.USER_WIN_MESSAGE
        userMove == rock && cpuMove == paper -> RPSMessages.USER_LOSE_MESSAGE
        userMove == paper && cpuMove == rock -> RPSMessages.USER_WIN_MESSAGE
        userMove == paper && cpuMove == scissors -> RPSMessages.USER_LOSE_MESSAGE
        userMove == scissors && cpuMove == paper -> RPSMessages.USER_WIN_MESSAGE
        userMove == scissors && cpuMove == rock -> RPSMessages.USER_LOSE_MESSAGE
        else -> RPSMessages.ERROR_MESSAGE
    }
}