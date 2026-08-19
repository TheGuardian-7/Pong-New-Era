package com.pongnewera.game.system

import com.pongnewera.game.PongGame
import com.pongnewera.game.Paddle
import com.pongnewera.input.PlayerInput

class PaddleMovementSystem {

    fun update(
        leftPaddle: Paddle,
        rightPaddle: Paddle,
        input: PlayerInput
    ) {
        updatePaddle(
            paddle = leftPaddle,
            targetY = input.leftPaddleY
        )

        updatePaddle(
            paddle = rightPaddle,
            targetY = input.rightPaddleY
        )
    }

    private fun updatePaddle(
        paddle: Paddle,
        targetY: Float?
    ) {
        targetY ?: return

        paddle.setY(
            targetY = targetY - paddle.height / 2f,
            minY = PongGame.FIELD_MARGIN,
            maxY = PongGame.WORLD_HEIGHT -
                PongGame.FIELD_MARGIN -
                paddle.height
        )
    }
}
