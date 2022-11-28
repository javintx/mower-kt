package com.mower.domain

import com.mower.domain.exception.UnknownCommandCode
import java.util.function.Consumer

enum class Command(private val code: String, private val action: Consumer<Mower>) {
    LEFT("L", { obj: Mower -> obj.spinLeft() }),
    RIGHT("R", { obj: Mower -> obj.spinRight() }),
    MOVE("M", { obj: Mower -> obj.moveForward() });

    companion object {
        private val commandCodes = values().associateBy(Command::code)
        fun fromCode(code: String): Command {
            return commandCodes[code] ?: throw UnknownCommandCode(code)
        }
    }

    fun execute(mower: Mower) {
        action.accept(mower)
    }
}
