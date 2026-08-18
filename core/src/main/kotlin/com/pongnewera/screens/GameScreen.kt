package com.pongnewera.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.graphics.OrthographicCamera

class GameScreen(
    private val game: Game
) : Screen {

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(800f, 480f, camera)

    override fun show() {
        viewport.apply()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
    }
}
