@file:JvmName("DesktopLauncher")

package com.mytest

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

fun main() {
    LwjglApplication(Game(), LwjglApplicationConfiguration().apply {
        title = Config.title
        fullscreen = Config.fullscreen
        width = Config.width
        height = Config.height
    })
}