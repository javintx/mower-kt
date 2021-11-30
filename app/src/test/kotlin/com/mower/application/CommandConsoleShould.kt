package com.mower.application

import com.mower.domain.CardinalPoint.NORTH
import com.mower.domain.Command
import com.mower.domain.Mower
import com.mower.domain.Plateau
import com.mower.domain.valueobjects.Coordinates
import com.mower.domain.valueobjects.FaceTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.internal.verification.Times
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Scanner
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
internal class CommandConsoleShould {
    private val scannerMocked: Scanner = mock(Scanner::class.java)
    private lateinit var commandConsole: CommandConsole
    private val anyCoordinateX = 1
    private val anyCoordinateY = 2
    private val anyCardinalPoint = NORTH
    private val validInputPlateauDimensions = "5 5"
    private val invalidInputPlateauDimensions = "INVALID DIMENSIONS"
    private val plateauWidth = 5
    private val plateauHeight = 5
    private val inputMowerDefinitionOutsidePlateau = "6 6 N"
    private val inputMowerDefinitionInOccupiedCoordinate = "3 3 N"
    private val validInputMowerDefinition = "1 2 N"
    private val validMowerCoordinateX = 1
    private val validMowerCoordinateY = 2
    private val occupiedCoordinateX = 3
    private val occupiedCoordinateY = 3
    private val invalidInputMowerCommands = "INVALID MOWER COMMANDS"
    private val validInputMowerCommands = "LRM"
    private val expectedMowerCommands = listOf(Command.LEFT, Command.RIGHT, Command.MOVE)
    private val validInputIsFinished = "y"
    private val validInputIsNotFinished = "N"
    private val invalidInputIsFinished = "INVALID"
    private val anyErrorMessage = "ANY ERROR MESSAGE"
    private val anyMessage = "ANY MESSAGE"

    @BeforeEach
    fun setUp() {
        commandConsole = CommandConsole(scannerMocked)
    }

    @Test
    fun readPlateau() {
        `when`(scannerMocked.nextLine()).thenReturn(invalidInputPlateauDimensions, validInputPlateauDimensions)
        assertNotNull(commandConsole.readPlateau())
        verify(scannerMocked, Times(2)).nextLine()
    }

    @Test
    fun readMower() {
        `when`(scannerMocked.nextLine())
            .thenReturn(inputMowerDefinitionOutsidePlateau)
            .thenReturn(inputMowerDefinitionInOccupiedCoordinate)
            .thenReturn(validInputMowerDefinition)
        assertEquals(
            commandConsole.readMowerGiven(plateauWithOccupiedCoordinates()).coordinates(),
            validMowerCoordinates()
        )
        verify(scannerMocked, Times(3)).nextLine()
    }

    @Test
    fun readMowerCommands() {
        `when`(scannerMocked.nextLine()).thenReturn(invalidInputMowerCommands, validInputMowerCommands)
        assertEquals(commandConsole.readMowerCommands(), expectedMowerCommands())
        verify(scannerMocked, Times(2)).nextLine()
    }

    @Test
    fun readIsFinished() {
        `when`(scannerMocked.nextLine()).thenReturn(invalidInputIsFinished, validInputIsFinished)
        assertTrue(commandConsole.readIsFinished())
        verify(scannerMocked, Times(2)).nextLine()
    }

    @Test
    fun readIsNotFinished() {
        `when`(scannerMocked.nextLine()).thenReturn(validInputIsNotFinished)
        assertFalse(commandConsole.readIsFinished())
        verify(scannerMocked, Times(1)).nextLine()
    }

    @Test
    fun printSituationOfMower() {
        commandConsole.printSituationOf(anyMower())
    }

    @Test
    fun printMessage() {
        commandConsole.printMessage(anyMessage)
    }

    @Test
    fun printErrorMessage() {
        commandConsole.printErrorMessage(anyErrorMessage)
    }

    private fun anyMower(): Mower {
        return Mower(anyCoordinates(), anyFaceTo())
    }

    private fun anyCoordinates(): Coordinates {
        return Coordinates(anyCoordinateX, anyCoordinateY)
    }

    private fun anyFaceTo(): FaceTo {
        return FaceTo(anyCardinalPoint)
    }

    private fun plateauWithOccupiedCoordinates(): Plateau {
        val plateau = Plateau(plateauCoordinates())
        plateau.occupyCoordinate(occupiedCoordinates())
        return plateau
    }

    private fun plateauCoordinates(): Coordinates {
        return Coordinates(plateauWidth, plateauHeight)
    }

    private fun occupiedCoordinates(): Coordinates {
        return Coordinates(occupiedCoordinateX, occupiedCoordinateY)
    }

    private fun validMowerCoordinates(): Coordinates {
        return Coordinates(validMowerCoordinateX, validMowerCoordinateY)
    }

    private fun expectedMowerCommands(): List<Command> {
        return expectedMowerCommands
    }
}
