package com.mower.domain.valueobjects

import com.mower.domain.CardinalPoint
import com.mower.domain.exception.CoordinatesAreOutside
import com.mower.domain.exception.CoordinatesMustBePositiveNumbers
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals

class CoordinateShould {
    val EXPECTED_COORDINATES_SITUATION = "1 2"
    private val COORDINATE_ZERO = 0
    private val COORDINATE_X = 1
    private val COORDINATE_Y = 2
    private val OTHER_COORDINATE_X = 3
    private val OTHER_COORDINATE_Y = 4
    private val LESS_THAN_ZERO_DIMENSION = -1

    @Test
    fun throwCoordinatesMustBePositiveNumbersIfCreatesACoordinatesWithCoordinateXZeroOrLess() {
        assertFailsWith<CoordinatesMustBePositiveNumbers> {
            Coordinates(LESS_THAN_ZERO_DIMENSION, COORDINATE_Y)
        }
    }

    @Test
    fun throwCoordinatesMustBePositiveNumbersIfCreatesACoordinatesWithCoordinateYZeroOrLess() {
        assertFailsWith<CoordinatesMustBePositiveNumbers> {
            Coordinates(COORDINATE_X, LESS_THAN_ZERO_DIMENSION)
        }
    }

    @Test
    fun printSituation() {
        assertEquals(EXPECTED_COORDINATES_SITUATION, coordinates().situation())
    }

    @Test
    fun verifyInsideCoordinates() {
        coordinatesInside().verifyAreInside(zeroCoordinates(), otherCoordinates())
    }

    @Test
    fun throwCoordinatesAreOutsideWhenVerifyOutsideAboveCoordinatesByHeight() {
        assertFailsWith<CoordinatesAreOutside> {
            otherCoordinatesAboveByHeight().verifyAreInside(zeroCoordinates(), otherCoordinates())
        }
    }

    @Test
    fun throwCoordinatesAreOutsideWhenVerifyOutsideAboveCoordinatesByWidth() {
        assertFailsWith<CoordinatesAreOutside> {
            otherCoordinatesAboveByWidth().verifyAreInside(zeroCoordinates(), otherCoordinates())
        }
    }

    @Test
    fun throwCoordinatesAreOutsideWhenVerifyOutsideBelowCoordinatesByHeight() {
        assertFailsWith<CoordinatesAreOutside> {
            coordinatesBelowByHeight().verifyAreInside(coordinates(), otherCoordinates())
        }
    }

    @Test
    fun throwCoordinatesAreOutsideWhenVerifyOutsideBelowCoordinatesByWidth() {
        assertFailsWith<CoordinatesAreOutside> {
            coordinatesBelowByWidth().verifyAreInside(coordinates(), otherCoordinates())
        }
    }

    @Test
    fun moveTowardsNorth() {
        val coordinates = coordinates()
        coordinates.moveTowards(CardinalPoint.NORTH)
        assertEquals(coordinatesMovedToNorth(), coordinates)
    }

    @Test
    fun moveTowardsEast() {
        val coordinates = coordinates()
        coordinates.moveTowards(CardinalPoint.EAST)
        assertEquals(coordinatesMovedToEast(), coordinates)
    }

    @Test
    fun moveTowardsSouth() {
        val coordinates = coordinates()
        coordinates.moveTowards(CardinalPoint.SOUTH)
        assertEquals(coordinatesMovedToSouth(), coordinates)
    }

    @Test
    fun moveTowardsWest() {
        val coordinates = coordinates()
        coordinates.moveTowards(CardinalPoint.WEST)
        assertEquals(coordinatesMovedToWest(), coordinates)
    }

    @Test
    fun ensureTwoCoordinatesWithSameValuesAreEquals() {
        assertEquals(coordinates(), coordinates())
    }

    @Test
    fun ensureSameCoordinatesIsEqualsToItself() {
        val coordinates = coordinates()
        // assertThat(coordinates).isEqualsTo(coordinates) does not works for this case
        assertEquals(coordinates, coordinates)
    }

    @Test
    fun ensureOtherClassIsNotEqualsToCoordinates() {
        assertNotEquals(any(), coordinates())
    }

    @Test
    fun ensureTwoCoordinatesWithDifferentCoordinateXAreNotEquals() {
        assertNotEquals(coordinatesAboveByWidth(), coordinates())
    }

    @Test
    fun ensureTwoCoordinatesWithDifferentCoordinateYAreNotEquals() {
        assertNotEquals(coordinatesAboveByHeight(), coordinates())
    }

    @Test
    fun ensureTwoCoordinatesWithSameValuesHaveSameHashCode() {
        assertEquals(coordinates().hashCode(), coordinates().hashCode())
    }

    @Test
    fun ensureTwoCoordinatesWithDifferentValuesHaveDifferentHashCode() {
        assertNotEquals(otherCoordinates().hashCode(), coordinates().hashCode())
    }

    private fun zeroCoordinates(): Coordinates {
        return Coordinates(COORDINATE_ZERO, COORDINATE_ZERO)
    }

    private fun coordinates(): Coordinates {
        return Coordinates(COORDINATE_X, COORDINATE_Y)
    }

    private fun coordinatesInside(): Coordinates {
        return Coordinates(COORDINATE_X, COORDINATE_Y)
    }

    private fun otherCoordinatesAboveByHeight(): Coordinates {
        return Coordinates(OTHER_COORDINATE_X, OTHER_COORDINATE_Y + 1)
    }

    private fun otherCoordinatesAboveByWidth(): Coordinates {
        return Coordinates(OTHER_COORDINATE_X + 1, OTHER_COORDINATE_Y)
    }

    private fun coordinatesAboveByHeight(): Coordinates {
        return Coordinates(COORDINATE_X, COORDINATE_Y + 1)
    }

    private fun coordinatesAboveByWidth(): Coordinates {
        return Coordinates(COORDINATE_X + 1, COORDINATE_Y)
    }

    private fun coordinatesBelowByHeight(): Coordinates {
        return Coordinates(COORDINATE_X, COORDINATE_Y - 1)
    }

    private fun coordinatesBelowByWidth(): Coordinates {
        return Coordinates(COORDINATE_X - 1, COORDINATE_Y)
    }

    private fun otherCoordinates(): Coordinates {
        return Coordinates(OTHER_COORDINATE_X, OTHER_COORDINATE_Y)
    }

    private fun coordinatesMovedToNorth(): Coordinates {
        return Coordinates(COORDINATE_X, COORDINATE_Y + 1)
    }

    private fun coordinatesMovedToEast(): Coordinates {
        return Coordinates(COORDINATE_X + 1, COORDINATE_Y)
    }

    private fun coordinatesMovedToSouth(): Coordinates {
        return Coordinates(COORDINATE_X, COORDINATE_Y - 1)
    }

    private fun coordinatesMovedToWest(): Coordinates {
        return Coordinates(COORDINATE_X - 1, COORDINATE_Y)
    }
}
