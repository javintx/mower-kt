package com.mower.domain

import com.mower.domain.exception.CoordinatesAreOccupied
import com.mower.domain.valueobjects.Coordinates

class Plateau(private val upperRightCoordinates: Coordinates) {
    val ZERO_COORDINATE = 0
    private val bottomLeftCoordinates = Coordinates(ZERO_COORDINATE, ZERO_COORDINATE)
    private val usedCoordinates: MutableList<Coordinates> = ArrayList()

    fun verifyCoordinates(coordinates: Coordinates) {
        verifyAreInside(coordinates)
        verifyAreUnoccupied(coordinates)
    }

    fun occupyCoordinate(coordinates: Coordinates) {
        usedCoordinates.add(coordinates)
    }

    private fun verifyAreInside(coordinates: Coordinates) {
        coordinates.verifyAreInside(bottomLeftCoordinates, upperRightCoordinates)
    }

    private fun verifyAreUnoccupied(coordinates: Coordinates) {
        if (usedCoordinates.contains(coordinates)) {
            throw CoordinatesAreOccupied(coordinates.situation())
        }
    }
}
