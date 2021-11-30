package com.mower.application

import com.mower.domain.CardinalPoint.NORTH
import com.mower.domain.Command
import com.mower.domain.Command.LEFT
import com.mower.domain.Command.MOVE
import com.mower.domain.Command.RIGHT
import com.mower.domain.Mower
import com.mower.domain.Plateau
import com.mower.domain.valueobjects.Coordinates
import com.mower.domain.valueobjects.FaceTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ConsoleApplicationShould {
    private val commandConsole: CommandConsole = mock(CommandConsole::class.java)
    private lateinit var consoleApplication: ConsoleApplication
    private val anyWidth = 5
    private val anyHeight = 5
    private val anyInitialCoordinateX = 1
    private val anyInitialCoordinateY = 1
    private val anyOtherInitialCoordinateX = 1
    private val anyOtherInitialCoordinateY = 0
    private val anyCardinalPoint = NORTH
    private val anyMowerCommands = listOf(LEFT, RIGHT, MOVE)
    private val toOutsideMowerCommands = listOf(MOVE, MOVE, MOVE, MOVE, MOVE, MOVE, MOVE)
    private val noMowerCommands: List<Command> = emptyList()
    private val anyOtherMowerCommands = listOf(MOVE)
    private val finishedValue = true
    private val noFinishedValue = false

    @BeforeEach
    fun setUp() {
        consoleApplication = ConsoleApplication(commandConsole)
    }

    @Test
    fun executeMowerCommandsInPlateau() {
        val plateau = anyPlateau()
        val mower = anyMower()
        `when`(commandConsole.readPlateau()).thenReturn(plateau)
        `when`(commandConsole.readMowerGiven(plateau)).thenReturn(mower)
        `when`(commandConsole.readMowerCommands()).thenReturn(anyMowerCommands)
        `when`(commandConsole.readIsFinished()).thenReturn(finishedValue)
        consoleApplication.start()
        verify(commandConsole).printSituationOf(mower)
    }

    @Test
    fun executeMowerCommandsInPlateauThatGoOutsideOfThePlateau() {
        val plateau = anyPlateau()
        val mower = anyMower()
        `when`(commandConsole.readPlateau()).thenReturn(plateau)
        `when`(commandConsole.readMowerGiven(plateau)).thenReturn(mower)
        `when`(commandConsole.readMowerCommands()).thenReturn(toOutsideMowerCommands)
        `when`(commandConsole.readIsFinished()).thenReturn(finishedValue)
        consoleApplication.start()
        verify(commandConsole).printErrorMessage(ArgumentMatchers.any(String::class.java))
    }

    @Test
    fun executeMowerCommandsInPlateauWhereCrashesTwoMowers() {
        val plateau = anyPlateau()
        val mower = anyMower()
        val anyOtherMower = anyOtherMower()
        `when`(commandConsole.readPlateau()).thenReturn(plateau)
        `when`(commandConsole.readMowerGiven(plateau)).thenReturn(mower).thenReturn(anyOtherMower)
        `when`(commandConsole.readMowerCommands()).thenReturn(noMowerCommands).thenReturn(anyOtherMowerCommands)
        `when`(commandConsole.readIsFinished()).thenReturn(noFinishedValue).thenReturn(finishedValue)
        consoleApplication.start()
        verify(commandConsole).printSituationOf(mower)
        verify(commandConsole).printSituationOf(anyOtherMower)
        verify(commandConsole).printErrorMessage(ArgumentMatchers.any(String::class.java))
    }

    private fun anyPlateau(): Plateau {
        return Plateau(anyPlateauCoordinates())
    }

    private fun anyPlateauCoordinates(): Coordinates {
        return Coordinates(anyWidth, anyHeight)
    }

    private fun anyMower(): Mower {
        return Mower(anyCoordinates(), anyFaceTo())
    }

    private fun anyCoordinates(): Coordinates {
        return Coordinates(anyInitialCoordinateX, anyInitialCoordinateY)
    }

    private fun anyFaceTo(): FaceTo {
        return FaceTo(anyCardinalPoint)
    }

    private fun anyOtherMower(): Mower {
        return Mower(anyOtherCoordinates(), anyFaceTo())
    }

    private fun anyOtherCoordinates(): Coordinates {
        return Coordinates(anyOtherInitialCoordinateX, anyOtherInitialCoordinateY)
    }
}
