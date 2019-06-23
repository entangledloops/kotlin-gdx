package com.mytest

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.inject.Context

class Game : KtxGame<Screen>() {
    val context = Context()

    private lateinit var img: Texture

    override fun create() {
        context.register {
            bindSingleton<Batch>(SpriteBatch())
        }

        img = Texture("assets/badlogic.jpg")
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        context.inject<Batch>().apply {
            begin()
            draw(img, 0f, 0f)
            end()
        }
    }

    override fun dispose() {
        context.dispose()
        img.dispose()
    }
}
