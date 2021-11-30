package com.mower

import com.mower.application.CommandConsole
import com.mower.application.ConsoleApplication
import java.nio.charset.Charset
import java.util.Scanner

class MowerApp {
    private var consoleApplication = ConsoleApplication(systemInputCommandConsole())

    fun main() {
        consoleApplication.start()
    }

    fun initForTestPurposes(testConsoleApplication: ConsoleApplication) {
        consoleApplication = testConsoleApplication
    }

    private fun systemInputCommandConsole(): CommandConsole {
        return CommandConsole(Scanner(System.`in`, Charset.defaultCharset()))
    }
}
