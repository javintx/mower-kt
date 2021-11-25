package com.mower.domain.valueobjects

import com.mower.domain.CardinalPoint.EAST
import com.mower.domain.CardinalPoint.NORTH
import com.mower.domain.CardinalPoint.SOUTH
import com.mower.domain.CardinalPoint.WEST
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FaceShould {
    @Test
    fun returnNorthOrientation() {
        assertEquals(NORTH, faceToNorth().orientation())
    }

    @Test
    fun returnEastOrientation() {
        assertEquals(EAST, faceToEast().orientation())
    }

    @Test
    fun returnSouthOrientation() {
        assertEquals(SOUTH, faceToSouth().orientation())
    }

    @Test
    fun returnWestOrientation() {
        assertEquals(WEST, faceToWest().orientation())
    }

    @Test
    fun spinRightFromNorthToEast() {
        val faceToNorth = faceToNorth()
        faceToNorth.spinRight()
        assertEquals(faceToEast().orientation(), faceToNorth.orientation())
    }

    @Test
    fun spinRightFromEastToSouth() {
        val faceToEast = faceToEast()
        faceToEast.spinRight()
        assertEquals(faceToEast.orientation(), faceToSouth().orientation())
    }

    @Test
    fun spinRightFromSouthToWest() {
        val faceToSouth = faceToSouth()
        faceToSouth.spinRight()
        assertEquals(faceToSouth.orientation(), faceToWest().orientation())
    }

    @Test
    fun spinRightFromWestToNorth() {
        val faceToWest = faceToWest()
        faceToWest.spinRight()
        assertEquals(faceToWest.orientation(), faceToNorth().orientation())
    }

    @Test
    fun spinLeftFromNorthToWest() {
        val faceToNorth = faceToNorth()
        faceToNorth.spinLeft()
        assertEquals(faceToNorth.orientation(), faceToWest().orientation())
    }

    @Test
    fun spinLeftFromEastToNorth() {
        val faceToEast = faceToEast()
        faceToEast.spinLeft()
        assertEquals(faceToEast.orientation(), faceToNorth().orientation())
    }

    @Test
    fun spinLeftFromSouthToEast() {
        val faceToSouth = faceToSouth()
        faceToSouth.spinLeft()
        assertEquals(faceToEast().orientation(), faceToSouth.orientation())
    }

    @Test
    fun spinLeftFromWestToSouth() {
        val faceToWest = faceToWest()
        faceToWest.spinLeft()
        assertEquals(faceToSouth().orientation(), faceToWest.orientation())
    }

    @Test
    fun printFaceToNorthSituation() {
        assertEquals(NORTH.code, faceToNorth().situation())
    }

    @Test
    fun printFaceToEastSituation() {
        assertEquals(EAST.code, faceToEast().situation())
    }

    @Test
    fun printFaceToSouthSituation() {
        assertEquals(SOUTH.code, faceToSouth().situation())
    }

    @Test
    fun printFaceToWestSituation() {
        assertEquals(WEST.code, faceToWest().situation())
    }

    private fun faceToNorth(): FaceTo {
        return FaceTo(NORTH)
    }

    private fun faceToEast(): FaceTo {
        return FaceTo(EAST)
    }

    private fun faceToSouth(): FaceTo {
        return FaceTo(SOUTH)
    }

    private fun faceToWest(): FaceTo {
        return FaceTo(WEST)
    }
}
