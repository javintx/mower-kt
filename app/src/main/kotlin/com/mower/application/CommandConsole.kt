package com.mower.application

import com.mower.domain.CardinalPoint.Companion.fromCode
import com.mower.domain.Command
import com.mower.domain.Mower
import com.mower.domain.Plateau
import com.mower.domain.valueobjects.Coordinates
import com.mower.domain.valueobjects.FaceTo
import java.util.Arrays
import java.util.Scanner
import java.util.stream.Collectors

class CommandConsole(private val scanner: Scanner) {

    private val PLATEAU_SEPARATOR_REGEX = Regex(" ")
    private val MOWER_SEPARATOR_REGEX = Regex(" ")
    private val COMMANDS_SEPARATOR_REGEX = Regex("")
    private val NUMBER_REGEX = "[0-9]+"

    private val MOWER_COORDINATE_X_INDEX = 0
    private val MOWER_COORDINATE_Y_INDEX = 1
    private val MOWER_CARDINAL_POINT_INDEX = 2
    private val FINISH_VALUE = "y"

    fun readPlateau(): Plateau {
        val plateauSize =
            parseArgument(
                "Insert the upper right coordinates of the plateau (like: 5 5): ",
                Regex("$NUMBER_REGEX$PLATEAU_SEPARATOR_REGEX$NUMBER_REGEX")
            )
        return processPlateauWith(plateauSize)
    }

    fun readMowerGiven(plateau: Plateau): Mower {
        var mowerDefinition: String
        do {
            mowerDefinition = parseArgument(
                "Insert the mower definition (like: 1 2 N): ",
                Regex("$NUMBER_REGEX$MOWER_SEPARATOR_REGEX$NUMBER_REGEX$MOWER_SEPARATOR_REGEX[NESW]")
            )
        } while (!verifyMower(plateau, mowerDefinition))
        return processMowerWith(mowerDefinition)
    }

    fun readMowerCommands(): List<Command> {
        val mowerCommands = parseArgument("Insert the mower commands (like: LRM): ", Regex("[LRM]+"))
        return processCommandsWith(mowerCommands)
    }

    fun readIsFinished(): Boolean {
        val isFinished = parseArgument("Do you want to finish? (y/n): ", Regex("[ynYN]"))
        return processIsFinishedWith(isFinished)
    }

    fun printSituationOf(mower: Mower) {
        printMessage(String.format("Mower situation: %s%n", mower.situation()))
    }

    fun printErrorMessage(error: String?) {
        System.err.println(error)
    }

    fun printMessage(message: String) {
        println(message)
    }

    private fun processPlateauWith(size: String): Plateau {
        return Plateau(processCoordinatesFrom(size))
    }

    private fun verifyMower(plateau: Plateau, mowerDefinition: String): Boolean {
        try {
            plateau.verifyCoordinates(processCoordinatesFrom(mowerDefinition))
            return true
        } catch (exception: Exception) {
            printErrorMessage(exception.message!!)
        }
        return false
    }

    private fun processCoordinatesFrom(coordinateDefinition: String): Coordinates {
        return Coordinates(
            coordinateXFrom(coordinateDefinition),
            coordinateYFrom(coordinateDefinition)
        )
    }

    private fun coordinateXFrom(mowerDefinition: String): Int {
        return mowerDefinition.split(MOWER_SEPARATOR_REGEX).toTypedArray()[MOWER_COORDINATE_X_INDEX].toInt()
    }

    private fun coordinateYFrom(mowerDefinition: String): Int {
        return mowerDefinition.split(MOWER_SEPARATOR_REGEX).toTypedArray()[MOWER_COORDINATE_Y_INDEX].toInt()
    }

    private fun processMowerWith(mowerDefinition: String): Mower {
        val mowerCoordinates = processCoordinatesFrom(mowerDefinition)
        return Mower(
            mowerCoordinates,
            processFaceToFrom(mowerDefinition)
        )
    }

    private fun processFaceToFrom(mowerDefinition: String): FaceTo {
        return FaceTo(fromCode(mowerDefinition.split(MOWER_SEPARATOR_REGEX).toTypedArray()[MOWER_CARDINAL_POINT_INDEX]))
    }

    private fun processCommandsWith(mowerCommands: String): List<Command> {
        return Arrays
            .stream(mowerCommands.split(COMMANDS_SEPARATOR_REGEX).filter { it.isNotBlank() }.toTypedArray())
            .map { commandCode: String ->
                Command.fromCode(
                    commandCode
                )
            }
            .collect(Collectors.toList())
    }

    private fun processIsFinishedWith(isFinished: String): Boolean {
        return FINISH_VALUE.equals(isFinished, ignoreCase = true)
    }

    private fun parseArgument(userExplanation: String, validationRegex: Regex): String {
        var argument: String
        do {
            argument = askForArgumentWith(userExplanation)
        } while (!validateArgument(validationRegex, argument))
        return argument
    }

    private fun askForArgumentWith(userExplanation: String): String {
        printMessage(userExplanation)
        return scanner.nextLine()
    }

    private fun validateArgument(validationRegex: Regex, argument: String): Boolean {
        return argument.matches(validationRegex)
    }
}
