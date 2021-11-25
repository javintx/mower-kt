package com.mower.usecase

import com.mower.domain.Command
import com.mower.domain.Command.LEFT
import com.mower.domain.Command.MOVE
import com.mower.domain.Command.RIGHT
import com.mower.domain.Mower
import com.mower.domain.Plateau
import com.mower.domain.exception.CoordinatesAreOccupied
import com.mower.domain.exception.CoordinatesAreOutside
import com.mower.domain.valueobjects.Coordinates
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.internal.verification.Times
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ExecuteMowerCommandsInPlateauShould {

    private val plateauMocked: Plateau = mock(Plateau::class.java)
    private val mowerMocked: Mower = mock(Mower::class.java)
    private val coordinatesMocked: Coordinates = mock(Coordinates::class.java)

    private var executeMowerCommandsInPlateau: ExecuteMowerCommandsInPlateau? = null

    @BeforeEach
    fun setUp() {
        executeMowerCommandsInPlateau = ExecuteMowerCommandsInPlateau()
    }

    @Test
    fun executeWithoutCommands() {
        `when`(mowerMocked.coordinates()).thenReturn(coordinatesMocked)
        executeMowerCommandsInPlateau!!.executeWith(plateauMocked, mowerMocked, emptyCommands())
        verify(
            plateauMocked,
            Times(emptyCommands().size)
        ).verifyCoordinates(ArgumentMatchers.any(Coordinates::class.java))
        verify(mowerMocked, Times(emptyCommands().size)).executeCommand(ArgumentMatchers.any(Command::class.java))
        verify(plateauMocked).occupyCoordinate(ArgumentMatchers.any(Coordinates::class.java))
    }

    @Test
    fun executeWithCommands() {
        `when`(mowerMocked.coordinates()).thenReturn(coordinatesMocked)
        executeMowerCommandsInPlateau!!.executeWith(plateauMocked, mowerMocked, commands())
        verify(
            plateauMocked,
            Times(commands().size)
        ).verifyCoordinates(ArgumentMatchers.any(Coordinates::class.java))
        verify(mowerMocked, Times(commands().size)).executeCommand(ArgumentMatchers.any(Command::class.java))
        verify(plateauMocked).occupyCoordinate(ArgumentMatchers.any(Coordinates::class.java))
    }

    @Test
    fun executeWithThrowCoordinatesAreOutsidePlateau() {
        `when`(mowerMocked.coordinates()).thenReturn(coordinatesMocked)
        doThrow(CoordinatesAreOutside::class.java).`when`(plateauMocked).verifyCoordinates(
            ArgumentMatchers.any(
                Coordinates::class.java
            )
        )
        Assertions.assertThrows(
            CoordinatesAreOutside::class.java
        ) {
            executeMowerCommandsInPlateau!!.executeWith(
                plateauMocked,
                mowerMocked,
                commands()
            )
        }
        verify(mowerMocked, Times(1)).executeCommand(ArgumentMatchers.any(Command::class.java))
        verify(mowerMocked, Times(1)).coordinates()
        verify(plateauMocked, Times(0)).occupyCoordinate(ArgumentMatchers.any(Coordinates::class.java))
    }

    @Test
    fun executeWithThrowCoordinatesAreOccupied() {
        `when`(mowerMocked.coordinates()).thenReturn(coordinatesMocked)
        doThrow(CoordinatesAreOccupied::class.java).`when`(plateauMocked).verifyCoordinates(
            ArgumentMatchers.any(
                Coordinates::class.java
            )
        )
        Assertions.assertThrows(
            CoordinatesAreOccupied::class.java
        ) {
            executeMowerCommandsInPlateau!!.executeWith(
                plateauMocked,
                mowerMocked,
                commands()
            )
        }
        verify(mowerMocked, Times(1)).executeCommand(ArgumentMatchers.any(Command::class.java))
        verify(mowerMocked, Times(1)).coordinates()
        verify(plateauMocked, Times(0)).occupyCoordinate(ArgumentMatchers.any(Coordinates::class.java))
    }

    private fun commands(): List<Command> {
        return listOf(LEFT, RIGHT, MOVE)
    }

    private fun emptyCommands(): List<Command> {
        return listOf()
    }
}
