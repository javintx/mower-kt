package com.mower.domain

import com.mower.domain.CardinalPoint.Companion.fromCode
import com.mower.domain.exception.UnknownCardinalPointCode
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertSame

class CardinalPointShould {
    private val UNKNOWN_CARDINAL_POINT_CODE = "UNKNOWN CARDINAL POINT CODE"

    @Test
    fun throwUnknownCardinalPointCodeIfCardinalPointCodeIsUnknown() {
        assertFailsWith<UnknownCardinalPointCode> {
            fromCode(UNKNOWN_CARDINAL_POINT_CODE)
        }
    }

    @Test
    fun returnNorthFromNCode() {
        assertSame(CardinalPoint.NORTH, fromCode("N"))
    }

    @Test
    fun returnEastFromECode() {
        assertSame(CardinalPoint.EAST, fromCode("E"))
    }

    @Test
    fun returnSouthFromSCode() {
        assertSame(CardinalPoint.SOUTH, fromCode("S"))
    }

    @Test
    fun returnWestFromWCode() {
        assertSame(CardinalPoint.WEST, fromCode("W"))
    }

    @Test
    fun returnNorthCode() {
        assertSame("N", CardinalPoint.NORTH.code)
    }

    @Test
    fun returnEastCode() {
        assertSame("E", CardinalPoint.EAST.code)
    }

    @Test
    fun returnSouthCode() {
        assertSame("S", CardinalPoint.SOUTH.code)
    }

    @Test
    fun returnWestCode() {
        assertSame("W", CardinalPoint.WEST.code)
    }
}
