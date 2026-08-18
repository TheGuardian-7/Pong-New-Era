package com.pongnewera.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector3
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

        private const val FIELD_MARGIN = 30f
    }

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
    private val shapeRenderer = ShapeRenderer()

    private val inputPosition = Vector3()

    private val leftPaddleX = 40f
    private val rightPaddleX = WORLD_WIDTH - 40f - PADDLE_WIDTH

    private var leftPaddleY = (WORLD_HEIGHT - PADDLE_HEIGHT) / 2f
    private var rightPaddleY = (WORLD_HEIGHT - PADDLE_HEIGHT) / 2f

    private val ballX = (WORLD_WIDTH - BALL_SIZE) / 2f
    private val ballY = (WORLD_HEIGHT - BALL_SIZE) / 2f

    override fun show() {
        viewport.apply()
    }

    override fun render(delta: Float) {
        updateTouchInput()

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()

        shapeRenderer.projectionMatrix = camera.combined
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)

        // Campo superior
        shapeRenderer.rect(
            FIELD_MARGIN,
            WORLD_HEIGHT - FIELD_MARGIN - 10f,
            WORLD_WIDTH - FIELD_MARGIN * 2f,
            10f
        )

        // Campo inferior
        shapeRenderer.rect(
            FIELD_MARGIN,
            FIELD_MARGIN,
            WORLD_WIDTH - FIELD_MARGIN * 2f,
            10f
        )

        // Paleta izquierda
        shapeRenderer.rect(
            leftPaddleX,
            leftPaddleY,
            PADDLE_WIDTH,
            PADDLE_HEIGHT
        )

        // Paleta derecha
        shapeRenderer.rect(
            rightPaddleX,
            rightPaddleY,
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

    private fun updateTouchInput() {
        for (pointer in 0 until 2) {
            if (!Gdx.input.isTouched(pointer)) {
                continue
            }

            inputPosition.set(
                Gdx.input.getX(pointer).toFloat(),
                Gdx.input.getY(pointer).toFloat(),
                0f
            )

            viewport.unproject(inputPosition)

            val worldX = inputPosition.x
            val worldY = inputPosition.y

            val paddleY = (worldY - PADDLE_HEIGHT / 2f).coerceIn(
                FIELD_MARGIN,
                WORLD_HEIGHT - FIELD_MARGIN - PADDLE_HEIGHT
            )

            if (worldX < WORLD_WIDTH / 2f) {
                leftPaddleY = paddleY
            } else {
                rightPaddleY = paddleY
            }
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
