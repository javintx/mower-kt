package com.mower.usecase

import com.mower.domain.Command
import com.mower.domain.Mower
import com.mower.domain.Plateau

class ExecuteMowerCommandsInPlateau {
    fun executeWith(plateau: Plateau, mower: Mower, commands: List<Command>) {
        for (command in commands) {
            mower.executeCommand(command)
            plateau.verifyCoordinates(mower.coordinates())
        }
        plateau.occupyCoordinate(mower.coordinates())
    }
}
