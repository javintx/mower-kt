package com.mower.domain.valueobjects

import com.mower.domain.CardinalPoint
import com.mower.domain.CardinalPoint.NORTH
import com.mower.domain.exception.CoordinatesAreOutside
import com.mower.domain.exception.CoordinatesMustBePositiveNumbers
import java.util.Map
import java.util.Objects
import java.util.function.Consumer
import kotlin.coroutines.coroutineContext

class Coordinates(private var coordinateX: Int, private var coordinateY: Int) {
    // val map = mapOf(1 to "x", 2 to "y", -1 to "zz")

    private val MOVEMENTS = Map.of(
        NORTH, Consumer { obj: Coordinates -> obj.upward() },
        CardinalPoint.EAST, Consumer { obj: Coordinates -> obj.forward() },
        CardinalPoint.SOUTH, Consumer { obj: Coordinates -> obj.downward() },
        CardinalPoint.WEST, Consumer { obj: Coordinates -> obj.backward() }
    )

    private val MINIMUM_COORDINATE_VALUE = 0

    init {
        validatePositiveCoordinates(coordinateX, coordinateY)
    }

    fun moveTowards(cardinalPoint: CardinalPoint?) {
        MOVEMENTS[cardinalPoint]?.accept(this)
    }

    fun verifyAreInside(bottomLeftCoordinates: Coordinates, upperRightCoordinates: Coordinates) {
        if (bottomLeftCoordinates.coordinateX > coordinateX ||
            bottomLeftCoordinates.coordinateY > coordinateY ||
            upperRightCoordinates.coordinateX < coordinateX ||
            upperRightCoordinates.coordinateY < coordinateY
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
        if (initialCoordinateX < MINIMUM_COORDINATE_VALUE || initialCoordinateY < MINIMUM_COORDINATE_VALUE) {
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
