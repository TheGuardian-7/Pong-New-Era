package com.pongnewera.rendering

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.pongnewera.game.PongGame

class GameRenderer(
    private val shapeRenderer: ShapeRenderer
) {

    fun render(game: PongGame) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)

        val fieldMargin = PongGame.FIELD_MARGIN
        val worldWidth = PongGame.WORLD_WIDTH
        val worldHeight = PongGame.WORLD_HEIGHT

        // Campo superior
        shapeRenderer.rect(
            fieldMargin,
            worldHeight - fieldMargin - 10f,
            worldWidth - fieldMargin * 2f,
            10f
        )

        // Campo inferior
        shapeRenderer.rect(
            fieldMargin,
            fieldMargin,
            worldWidth - fieldMargin * 2f,
            10f
        )

        // Paleta izquierda
        shapeRenderer.rect(
            game.leftPaddle.x,
            game.leftPaddle.y,
            game.leftPaddle.width,
            game.leftPaddle.height
        )

        // Paleta derecha
        shapeRenderer.rect(
            game.rightPaddle.x,
            game.rightPaddle.y,
            game.rightPaddle.width,
            game.rightPaddle.height
        )

        // Pelota
        shapeRenderer.rect(
            game.ball.x,
            game.ball.y,
            game.ball.size,
            game.ball.size
        )

        shapeRenderer.end()
    }
}
