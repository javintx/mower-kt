package com.mower.domain

import com.mower.domain.valueobjects.Coordinates
import com.mower.domain.valueobjects.FaceTo

class Mower(private val coordinates: Coordinates, private val faceTo: FaceTo) {

    fun coordinates(): Coordinates {
        return coordinates
    }

    fun executeCommand(command: Command) {
        command.execute(this)
    }

    fun moveForward() {
        coordinates.moveTowards(faceTo.orientation())
    }

    fun spinRight() {
        faceTo.spinRight()
    }

    fun spinLeft() {
        faceTo.spinLeft()
    }

    fun situation(): String {
        return "${coordinates.situation()} ${faceTo.situation()}"
    }
}
