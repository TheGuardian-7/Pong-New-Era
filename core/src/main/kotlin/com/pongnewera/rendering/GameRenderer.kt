package com.pongnewera.rendering

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.pongnewera.game.GameConfig
import com.pongnewera.game.PongGame

class GameRenderer(
    private val shapeRenderer: ShapeRenderer,
    private val config: GameConfig
) {

    fun render(game: PongGame) {
        shapeRenderer.begin(
            ShapeRenderer.ShapeType.Filled
        )

        renderField()
        renderPaddles(game)
        renderBall(game)

        shapeRenderer.end()
    }

    private fun renderField() {
        shapeRenderer.rect(
            config.fieldMargin,
            config.worldHeight -
                config.fieldMargin -
                10f,
            config.worldWidth -
                config.fieldMargin * 2f,
            10f
        )

        shapeRenderer.rect(
            config.fieldMargin,
            config.fieldMargin,
            config.worldWidth -
                config.fieldMargin * 2f,
            10f
        )
    }

    private fun renderPaddles(game: PongGame) {
        shapeRenderer.rect(
            game.leftPaddle.x,
            game.leftPaddle.y,
            game.leftPaddle.width,
            game.leftPaddle.height
        )

        shapeRenderer.rect(
            game.rightPaddle.x,
            game.rightPaddle.y,
            game.rightPaddle.width,
            game.rightPaddle.height
        )
    }

    private fun renderBall(game: PongGame) {
        shapeRenderer.rect(
            game.ball.x,
            game.ball.y,
            game.ball.size,
            game.ball.size
        )
    }
}
