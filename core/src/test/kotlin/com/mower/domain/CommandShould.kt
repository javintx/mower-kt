package com.mower.domain

import com.mower.domain.Command.Companion.fromCode
import com.mower.domain.Command.LEFT
import com.mower.domain.Command.MOVE
import com.mower.domain.Command.RIGHT
import com.mower.domain.exception.UnknownCommandCode
import com.mower.domain.valueobjects.Coordinates
import com.mower.domain.valueobjects.FaceTo
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertSame

class CommandShould {
    private val UNKNOWN_COMMAND_CODE = "UNKNOWN COMMAND CODE"
    private val COORDINATE_X = 1
    private val COORDINATE_Y = 1
    private val CARDINAL_POINT = CardinalPoint.NORTH

    @Test
    fun throwUnknownCommandCodeIfCardinalPointCodeIsUnknown() {
        assertFailsWith<UnknownCommandCode> {
            fromCode(UNKNOWN_COMMAND_CODE)
        }
    }

    @Test
    fun returnCommandLeftFromLCode() {
        assertSame(LEFT, fromCode("L"))
    }

    @Test
    fun returnCommandRightFromRCode() {
        assertSame(RIGHT, fromCode("R"))
    }

    @Test
    fun returnCommandMoveFromMCode() {
        assertSame(MOVE, fromCode("M"))
    }

    @Test
    fun executeLeftCommandAction() {
        LEFT.execute(mower())
    }

    @Test
    fun executeRightCommandAction() {
        RIGHT.execute(mower())
    }

    @Test
    fun executeMoveCommandAction() {
        MOVE.execute(mower())
    }

    private fun mower(): Mower {
        return Mower(coordinates(), faceTo())
    }

    private fun coordinates(): Coordinates {
        return Coordinates(COORDINATE_X, COORDINATE_Y)
    }

    private fun faceTo(): FaceTo {
        return FaceTo(CARDINAL_POINT)
    }
}
