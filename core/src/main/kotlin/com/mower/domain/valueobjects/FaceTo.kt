package com.mower.domain.valueobjects

import com.mower.domain.CardinalPoint

class FaceTo(private var cardinalPointOrientation: CardinalPoint) {
    private val FIRST_CARDINAL_POINT_INDEX = 0

    fun orientation(): CardinalPoint {
        return cardinalPointOrientation
    }

    fun spinRight() {
        cardinalPointOrientation = rightCardinalPoint()
    }

    fun spinLeft() {
        cardinalPointOrientation = leftCardinalPoint()
    }

    fun situation(): String {
        return cardinalPointOrientation.code
    }

    private fun rightCardinalPoint(): CardinalPoint {
        if (isTheLastCardinalPoint()) {
            return getFirstCardinalPoint()
        }
        val nextCardinalPositionIndex = cardinalPointOrientation.ordinal + 1
        return CardinalPoint.values()[nextCardinalPositionIndex]
    }

    private fun isTheLastCardinalPoint(): Boolean {
        return cardinalPointOrientation.ordinal == CardinalPoint.values().size - 1
    }

    private fun getFirstCardinalPoint(): CardinalPoint {
        return CardinalPoint.values()[FIRST_CARDINAL_POINT_INDEX]
    }

    private fun leftCardinalPoint(): CardinalPoint {
        if (isTheFirstCardinalPoint()) {
            return getLastCardinalPoint()
        }
        val previousCardinalPositionIndex = cardinalPointOrientation.ordinal - 1
        return CardinalPoint.values()[previousCardinalPositionIndex]
    }

    private fun isTheFirstCardinalPoint(): Boolean {
        return cardinalPointOrientation.ordinal == FIRST_CARDINAL_POINT_INDEX
    }

    private fun getLastCardinalPoint(): CardinalPoint {
        val previousCardinalPointIndex = CardinalPoint.values().size - 1
        return CardinalPoint.values()[previousCardinalPointIndex]
    }
}
