package com.pongnewera.game.system

import com.pongnewera.game.GameConfig
import com.pongnewera.game.Paddle
import com.pongnewera.input.PlayerInput

class PaddleMovementSystem(
    private val config: GameConfig
) {

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
            minY = config.fieldMargin,
            maxY = config.worldHeight -
                config.fieldMargin -
                paddle.height
        )
    }
}
