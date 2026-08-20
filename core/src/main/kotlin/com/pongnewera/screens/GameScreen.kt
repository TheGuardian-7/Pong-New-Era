package com.pongnewera.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.pongnewera.game.GameConfig
import com.pongnewera.game.MatchState
import com.pongnewera.game.PongGame
import com.pongnewera.input.TouchInput
import com.pongnewera.rendering.GameRenderer

class GameScreen(
    private val game: Game
) : Screen {

    private val config = GameConfig()

    private val camera = OrthographicCamera()

    private val viewport = FitViewport(
        config.worldWidth,
        config.worldHeight,
        camera
    )

    private val shapeRenderer = ShapeRenderer()

    private val pongGame = PongGame(
        config = config
    )

    private val touchInput = TouchInput(
        viewport = viewport,
        worldWidth = config.worldWidth
    )

    private val renderer = GameRenderer(
        shapeRenderer = shapeRenderer,
        config = config
    )

    override fun show() {
        viewport.apply()
    }

    override fun render(delta: Float) {
        handleInput()

        pongGame.update(
            delta = delta,
            input = touchInput.read()
        )

        clearScreen()

        camera.update()
        shapeRenderer.projectionMatrix = camera.combined

        renderer.render(pongGame)
    }

    private fun handleInput() {
        if (!Gdx.input.justTouched()) {
            return
        }

        when (pongGame.matchState) {
            MatchState.READY -> {
                pongGame.start()
            }

            MatchState.POINT_SCORED -> {
                pongGame.continueAfterPoint()
            }

            MatchState.PLAYING,
            MatchState.GAME_OVER -> {
            }
        }
    }

    private fun clearScreen() {
        Gdx.gl.glClearColor(
            0f,
            0f,
            0f,
            1f
        )

        Gdx.gl.glClear(
            GL20.GL_COLOR_BUFFER_BIT
        )
    }

    override fun resize(
        width: Int,
        height: Int
    ) {
        viewport.update(
            width,
            height,
            true
        )
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
