package com.pongnewera.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.pongnewera.game.MatchState
import com.pongnewera.game.PongGame
import com.pongnewera.input.TouchInput
import com.pongnewera.rendering.GameRenderer

class GameScreen(
    private val game: Game
) : Screen {

    private val camera = OrthographicCamera()

    private val viewport = FitViewport(
        PongGame.WORLD_WIDTH,
        PongGame.WORLD_HEIGHT,
        camera
    )

    private val shapeRenderer = ShapeRenderer()

    private val pongGame = PongGame()

    private val touchInput = TouchInput(
        viewport = viewport,
        worldWidth = PongGame.WORLD_WIDTH
    )

    private val renderer = GameRenderer(shapeRenderer)

    override fun show() {
        viewport.apply()
    }

    override fun render(delta: Float) {
        handleStartInput()

        pongGame.update(
            delta = delta,
            input = touchInput.read()
        )

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
        shapeRenderer.projectionMatrix = camera.combined

        renderer.render(pongGame)
    }

    private fun handleStartInput() {
        if (pongGame.matchState != MatchState.READY) {
            return
        }

        if (Gdx.input.justTouched()) {
            pongGame.start()
        }
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
        shapeRenderer.dispose()
    }
}
