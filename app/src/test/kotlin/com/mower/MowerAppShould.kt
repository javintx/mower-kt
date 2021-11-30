package com.mower

import com.mower.application.ConsoleApplication
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MowerAppShould {
    private val consoleApplicationMocked: ConsoleApplication = mock(ConsoleApplication::class.java)

    @Test
    fun main() {
        doNothing().`when`(consoleApplicationMocked).start()
        val mower = MowerApp()
        mower.initForTestPurposes(consoleApplicationMocked)
        mower.main()
        Mockito.verify(consoleApplicationMocked).start()
    }
}
