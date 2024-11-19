package jr.brian.rps.data

import androidx.annotation.DrawableRes
import jr.brian.rps.R

data class RPSMove(
    val moveName: String,
    @DrawableRes val drawable: Int
) {
    companion object {
        val EMPTY = RPSMove(
            "",
            0
        )
    }
}

val rpsMoves = listOf(
    RPSMove(
        moveName = "Rock",
        drawable = R.drawable.rock
    ),
    RPSMove(
        moveName = "Paper",
        drawable = R.drawable.paper
    ),
    RPSMove(
        moveName = "Scissors",
        drawable = R.drawable.scissors
    )
)
