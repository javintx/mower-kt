package com.mower.domain.valueobjects

import com.mower.domain.CardinalPoint
import com.mower.domain.CardinalPoint.*
import com.mower.domain.exception.CoordinatesAreOutside
import com.mower.domain.exception.CoordinatesMustBePositiveNumbers
import java.util.*
import java.util.function.Consumer

class Coordinates(private var coordinateX: Int, private var coordinateY: Int) {
    private val movements = mapOf(
        NORTH to Consumer { obj: Coordinates -> obj.upward() },
        EAST to Consumer { obj: Coordinates -> obj.forward() },
        SOUTH to Consumer { obj: Coordinates -> obj.downward() },
        WEST to Consumer { obj: Coordinates -> obj.backward() }
    )

    private val minimumCoordinateValue = 0

    init {
        validatePositiveCoordinates(coordinateX, coordinateY)
    }

    fun moveTowards(cardinalPoint: CardinalPoint) {
        movements[cardinalPoint]?.accept(this)
    }

    fun verifyAreInside(bottomLeftCoordinates: Coordinates, upperRightCoordinates: Coordinates) {
        if (coordinateX in bottomLeftCoordinates.coordinateX..upperRightCoordinates.coordinateX ||
            coordinateY in bottomLeftCoordinates.coordinateY..upperRightCoordinates.coordinateY
        ) {
            throw CoordinatesAreOutside(coordinateX, coordinateY)
        }
    }

    fun situation(): String {
        return "$coordinateX $coordinateY"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val that = other as Coordinates
        return coordinateX == that.coordinateX && coordinateY == that.coordinateY
    }

    override fun hashCode(): Int {
        return Objects.hash(coordinateX, coordinateY)
    }

    private fun validatePositiveCoordinates(initialCoordinateX: Int, initialCoordinateY: Int) {
        if (initialCoordinateX < minimumCoordinateValue || initialCoordinateY < minimumCoordinateValue) {
            throw CoordinatesMustBePositiveNumbers(initialCoordinateX, initialCoordinateY)
        }
    }

    private fun forward() {
        coordinateX++
    }

    private fun backward() {
        coordinateX--
    }

    private fun upward() {
        coordinateY++
    }

    private fun downward() {
        coordinateY--
    }
}
