package com.mower.domain

import com.mower.domain.exception.UnknownCardinalPointCode
import java.util.Arrays

enum class CardinalPoint(val code: String) {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

    companion object {
        fun fromCode(cardinalPointCode: String): CardinalPoint {
            return Arrays.stream(values())
                .filter { cardinalPoint: CardinalPoint ->
                    cardinalPoint.code == cardinalPointCode
                }
                .findAny()
                .orElseThrow { UnknownCardinalPointCode(cardinalPointCode) }
        }
    }
}
