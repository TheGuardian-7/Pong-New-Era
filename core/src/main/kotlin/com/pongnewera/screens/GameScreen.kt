package com.pongnewera.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport

class GameScreen(
    private val game: Game
) : Screen {

    companion object {
        private const val WORLD_WIDTH = 800f
        private const val WORLD_HEIGHT = 480f

        private const val PADDLE_WIDTH = 12f
        private const val PADDLE_HEIGHT = 80f
        private const val BALL_SIZE = 12f
    }

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
    private val shapeRenderer = ShapeRenderer()

    private val leftPaddleX = 40f
    private val rightPaddleX = WORLD_WIDTH - 40f - PADDLE_WIDTH
    private val paddleY = (WORLD_HEIGHT - PADDLE_HEIGHT) / 2f

    private val ballX = (WORLD_WIDTH - BALL_SIZE) / 2f
    private val ballY = (WORLD_HEIGHT - BALL_SIZE) / 2f

    override fun show() {
        viewport.apply()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()

        shapeRenderer.projectionMatrix = camera.combined
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)

        // Campo superior
        shapeRenderer.rect(
            20f,
            WORLD_HEIGHT - 30f,
            WORLD_WIDTH - 40f,
            10f
        )

        // Campo inferior
        shapeRenderer.rect(
            20f,
            20f,
            WORLD_WIDTH - 40f,
            10f
        )

        // Paleta izquierda
        shapeRenderer.rect(
            leftPaddleX,
            paddleY,
            PADDLE_WIDTH,
            PADDLE_HEIGHT
        )

        // Paleta derecha
        shapeRenderer.rect(
            rightPaddleX,
            paddleY,
            PADDLE_WIDTH,
            PADDLE_HEIGHT
        )

        // Pelota
        shapeRenderer.rect(
            ballX,
            ballY,
            BALL_SIZE,
            BALL_SIZE
        )

        shapeRenderer.end()
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
