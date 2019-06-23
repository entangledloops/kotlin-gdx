package com.mytest

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.app.KtxGame
import ktx.inject.Context
import com.badlogic.gdx.assets.loaders.SkinLoader
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.ScreenViewport

class Game : KtxGame<Screen>() {
    val context = Context()

    private lateinit var img: Texture

    override fun create() {
        context.register {
            bindSingleton<FileHandleResolver>(InternalFileHandleResolver())
            bindSingleton(AssetManager().apply {
                setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(inject()))
                setLoader(BitmapFont::class.java, ".ttf", FreetypeFontLoader(inject()))
                setLoader(BitmapFont::class.java, ".fnt", BitmapFontLoader(inject()))
                setLoader(Skin::class.java, ".json", SkinLoader(inject()))
            })
            bindSingleton<Camera>(OrthographicCamera())
            bindSingleton<Batch>(SpriteBatch())
            bindSingleton<Viewport>(ScreenViewport(inject()))
            bindSingleton(Stage(inject(), inject()))
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
