package com.mower.domain

import com.mower.domain.exception.CoordinatesAreOccupied
import com.mower.domain.exception.CoordinatesAreOutside
import com.mower.domain.valueobjects.Coordinates
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class PlateauShould {
    private val VALID_PLATEAU_WIDTH = 5
    private val VALID_PLATEAU_HEIGHT = 5
    private val OUTSIDE_COORDINATE_X = 6
    private val OUTSIDE_COORDINATE_Y = 6
    private val VALID_COORDINATE_X = 3
    private val VALID_COORDINATE_Y = 3

    @Test
    fun throwCoordinatesAreOutsidePlateauIfOutsizeCoordinates() {
        assertFailsWith<CoordinatesAreOutside> {
            validPlateau().verifyCoordinates(outsideCoordinates())
        }
    }

    @Test
    fun throwCoordinatesAreOutsidePlateauIfOutsizeCoordinatesByCoordinateX() {
        assertFailsWith<CoordinatesAreOutside> {
            validPlateau().verifyCoordinates(outsideCoordinatesByCoordinateX())
        }
    }

    @Test
    fun throwCoordinatesAreOutsidePlateauIfOutsizeCoordinatesByCoordinateY() {
        assertFailsWith<CoordinatesAreOutside> {
            validPlateau().verifyCoordinates(outsideCoordinatesByCoordinateY())
        }
    }

    @Test
    fun throwCoordinatesAreOccupiedIfCoordinatesAreOccupiedInThePlateau() {
        assertFailsWith<CoordinatesAreOccupied> {
            validPlateauWithOccupiedCoordinate().verifyCoordinates(validCoordinates())
        }
    }

    @Test
    fun verifyCoordinates() {
        validPlateau().verifyCoordinates(validCoordinates())
    }

    private fun validPlateau(): Plateau {
        return Plateau(validPlateauCoordinates())
    }

    private fun validPlateauWithOccupiedCoordinate(): Plateau {
        val plateau = Plateau(validPlateauCoordinates())
        plateau.occupyCoordinate(validCoordinates())
        return plateau
    }

    private fun validPlateauCoordinates(): Coordinates {
        return Coordinates(VALID_PLATEAU_WIDTH, VALID_PLATEAU_HEIGHT)
    }

    private fun outsideCoordinates(): Coordinates {
        return Coordinates(OUTSIDE_COORDINATE_X, OUTSIDE_COORDINATE_Y)
    }

    private fun outsideCoordinatesByCoordinateX(): Coordinates {
        return Coordinates(OUTSIDE_COORDINATE_X, VALID_COORDINATE_Y)
    }

    private fun outsideCoordinatesByCoordinateY(): Coordinates {
        return Coordinates(VALID_COORDINATE_X, OUTSIDE_COORDINATE_Y)
    }

    private fun validCoordinates(): Coordinates {
        return Coordinates(VALID_COORDINATE_X, VALID_COORDINATE_Y)
    }
}
