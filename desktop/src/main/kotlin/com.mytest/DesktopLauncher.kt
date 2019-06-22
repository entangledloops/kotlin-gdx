package com.mytest

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.fullscreen = false
        config.width = 750
        config.height = 1334
        LwjglApplication(Game(), config)
    }
}
