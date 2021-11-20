package com.mower.domain

import com.mower.domain.exception.UnknownCommandCode
import java.util.Arrays
import java.util.function.Consumer

enum class Command(private val code: String, private val action: Consumer<Mower>) {
    LEFT("L", Consumer { obj: Mower -> obj.spinLeft() }),
    RIGHT("R", Consumer { obj: Mower -> obj.spinRight() }),
    MOVE("M", Consumer { obj: Mower -> obj.moveForward() });

    companion object {
        fun fromCode(commandCode: String): Command {
            return Arrays.stream(values())
                .filter { command: Command ->
                    command.code == commandCode
                }
                .findAny()
                .orElseThrow { UnknownCommandCode(commandCode) }
        }
    }

    fun execute(mower: Mower) {
        action.accept(mower)
    }
}
