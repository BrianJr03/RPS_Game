package jr.brian.rps

import androidx.compose.ui.graphics.Color
import jr.brian.rps.data.RPSMessages
import jr.brian.rps.data.paper
import jr.brian.rps.data.rock
import jr.brian.rps.data.scissors
import jr.brian.rps.util.generateOutcome
import jr.brian.rps.util.getOutcomeTextColor
import org.junit.Assert.assertEquals
import org.junit.Test

class RPSTest {
    @Test
    fun generateOutcomeTest() {
        val errorOutcome1 = generateOutcome(userMove = "", cpuMove = rock)
        val errorOutcome2 = generateOutcome(userMove = rock + paper, cpuMove = rock)
        assertEquals(RPSMessages.ERROR_MESSAGE, errorOutcome1)
        assertEquals(RPSMessages.ERROR_MESSAGE, errorOutcome2)
        assertEquals(Color.Red, getOutcomeTextColor(errorOutcome1))
        assertEquals(Color.Red, getOutcomeTextColor(errorOutcome2))

        val tieOutcome1 = generateOutcome(userMove = rock, cpuMove = rock)
        val tieOutcome2 = generateOutcome(userMove = paper, cpuMove = paper)
        val tieOutcome3 = generateOutcome(userMove = scissors, cpuMove = scissors)
        assertEquals(RPSMessages.TIE_MESSAGE, tieOutcome1)
        assertEquals(RPSMessages.TIE_MESSAGE, tieOutcome2)
        assertEquals(RPSMessages.TIE_MESSAGE, tieOutcome3)
        assertEquals(Color.Gray, getOutcomeTextColor(tieOutcome1))
        assertEquals(Color.Gray, getOutcomeTextColor(tieOutcome2))
        assertEquals(Color.Gray, getOutcomeTextColor(tieOutcome3))

        val userWinOutcome1 = generateOutcome(userMove = rock, cpuMove = scissors)
        val userWinOutcome2 = generateOutcome(userMove = paper, cpuMove = rock)
        val userWinOutcome3 = generateOutcome(userMove = scissors, cpuMove = paper)
        assertEquals(RPSMessages.USER_WIN_MESSAGE, userWinOutcome1)
        assertEquals(RPSMessages.USER_WIN_MESSAGE, userWinOutcome2)
        assertEquals(RPSMessages.USER_WIN_MESSAGE, userWinOutcome3)
        assertEquals(Color.Green, getOutcomeTextColor(userWinOutcome1))
        assertEquals(Color.Green, getOutcomeTextColor(userWinOutcome2))
        assertEquals(Color.Green, getOutcomeTextColor(userWinOutcome3))

        val cpuWinOutcome1 = generateOutcome(cpuMove = paper, userMove = rock)
        val cpuWinOutcome2 = generateOutcome(cpuMove = scissors, userMove = paper)
        val cpuWinOutcome3 = generateOutcome(cpuMove = rock, userMove = scissors)
        assertEquals(RPSMessages.USER_LOSE_MESSAGE, cpuWinOutcome1)
        assertEquals(RPSMessages.USER_LOSE_MESSAGE, cpuWinOutcome2)
        assertEquals(RPSMessages.USER_LOSE_MESSAGE, cpuWinOutcome3)
        assertEquals(Color.Red, getOutcomeTextColor(cpuWinOutcome1))
        assertEquals(Color.Red, getOutcomeTextColor(cpuWinOutcome2))
        assertEquals(Color.Red, getOutcomeTextColor(cpuWinOutcome3))
    }
}