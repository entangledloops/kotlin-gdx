package com.mytest

import com.mytest.Game
import kotlin.test.Test
import kotlin.test.assertNotNull

class DesktopLauncherTest {
    @Test fun testGame() {
        val classUnderTest = Game()
        assertNotNull(classUnderTest)
    }
}
