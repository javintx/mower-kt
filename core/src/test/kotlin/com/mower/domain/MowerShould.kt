package com.mower.domain

import com.mower.domain.valueobjects.Coordinates
import com.mower.domain.valueobjects.FaceTo
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.test.assertEquals

class MowerShould {

    private val EXPECTED_MOWER_SITUATION = "1 2 N"
    private val COORDINATE_X = 1
    private val COORDINATE_Y = 2
    private val CARDINAL_POINT = CardinalPoint.NORTH

    private val commandMocked: Command = mock(Command::class.java)
    private val faceToMocked: FaceTo = mock(FaceTo::class.java)
    private val coordinatesMocked: Coordinates = mock(Coordinates::class.java)

    @Test
    fun returnCoordinates() {
        assertEquals(anyCoordinates(), anyMower().coordinates())
    }

    @Test
    fun executeCommand() {
        Mockito.doNothing().`when`(commandMocked).execute(ArgumentMatchers.any(Mower::class.java))
        anyMower().executeCommand(commandMocked)
        Mockito.verify(commandMocked).execute(ArgumentMatchers.any(Mower::class.java))
    }

    @Test
    fun spinLeft() {
        Mockito.doNothing().`when`(faceToMocked).spinLeft()
        anyMowerWithFaceToMocked().spinLeft()
        Mockito.verify(faceToMocked).spinLeft()
    }

    @Test
    fun spinRight() {
        Mockito.doNothing().`when`(faceToMocked).spinRight()
        anyMowerWithFaceToMocked().spinRight()
        Mockito.verify(faceToMocked).spinRight()
    }

    @Test
    fun moveForward() {
        Mockito.doNothing().`when`(coordinatesMocked).moveTowards(
            ArgumentMatchers.any(
                CardinalPoint::class.java
            )
        )
        anyMowerWithCoordinatesMocked().moveForward()
        Mockito.verify(coordinatesMocked).moveTowards(ArgumentMatchers.any(CardinalPoint::class.java))
    }

    @Test
    fun returnSituation() {
        assertEquals(EXPECTED_MOWER_SITUATION, anyMower().situation())
    }

    private fun anyMower(): Mower {
        return Mower(anyCoordinates(), anyFaceTo())
    }

    private fun anyMowerWithFaceToMocked(): Mower {
        return Mower(anyCoordinates(), faceToMocked)
    }

    private fun anyMowerWithCoordinatesMocked(): Mower {
        return Mower(coordinatesMocked, anyFaceTo())
    }

    private fun anyCoordinates(): Coordinates {
        return Coordinates(COORDINATE_X, COORDINATE_Y)
    }

    private fun anyFaceTo(): FaceTo {
        return FaceTo(CARDINAL_POINT)
    }
}
