package com.mower.application

import com.mower.domain.Mower
import com.mower.domain.Plateau
import com.mower.domain.exception.CoordinatesAreOccupied
import com.mower.domain.exception.CoordinatesAreOutside
import com.mower.usecase.ExecuteMowerCommandsInPlateau

class ConsoleApplication(private val commandConsole: CommandConsole) {

    fun start() {
        val plateau = commandConsole.readPlateau()
        val executeMowerCommandsInPlateau = ExecuteMowerCommandsInPlateau()
        readAndExecuteCommands(commandConsole, plateau, executeMowerCommandsInPlateau)
    }

    private fun readAndExecuteCommands(
        commandConsole: CommandConsole,
        plateau: Plateau,
        executeMowerCommandsInPlateau: ExecuteMowerCommandsInPlateau
    ) {
        do {
            readAndExecuteCommand(commandConsole, plateau, executeMowerCommandsInPlateau)
        } while (!commandConsole.readIsFinished())
    }

    private fun readAndExecuteCommand(
        commandConsole: CommandConsole,
        plateau: Plateau,
        executeMowerCommandsInPlateau: ExecuteMowerCommandsInPlateau
    ) {
        val mower = commandConsole.readMowerGiven(plateau)
        val mowerCommands = commandConsole.readMowerCommands()
        try {
            executeMowerCommandsInPlateau.executeWith(plateau, mower, mowerCommands)
            commandConsole.printSituationOf(mower)
        } catch (coordinatesAreOutside: CoordinatesAreOutside) {
            mowerHasComeOffThePlateau(commandConsole, coordinatesAreOutside)
        } catch (coordinatesAreOccupied: CoordinatesAreOccupied) {
            mowerHasCrashedWithAnotherMower(commandConsole, mower, coordinatesAreOccupied)
        }
    }

    private fun mowerHasCrashedWithAnotherMower(
        commandConsole: CommandConsole,
        mower: Mower,
        coordinatesAreOccupied: CoordinatesAreOccupied
    ) {
        commandConsole.printErrorMessage(coordinatesAreOccupied.message)
        commandConsole.printSituationOf(mower)
    }

    private fun mowerHasComeOffThePlateau(
        commandConsole: CommandConsole,
        coordinatesAreOutside: CoordinatesAreOutside
    ) {
        commandConsole.printErrorMessage(coordinatesAreOutside.message)
    }
}
