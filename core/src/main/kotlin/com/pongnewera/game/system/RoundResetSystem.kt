package com.pongnewera.game.system

import com.pongnewera.game.entity.Ball
import com.pongnewera.game.GameConfig
import com.pongnewera.game.entity.Paddle
import kotlin.math.abs

class RoundResetSystem(
    private val config: GameConfig
) {

    fun reset(
        ball: Ball,
        leftPaddle: Paddle,
        rightPaddle: Paddle,
        serveDirection: Float
    ) {
        resetBall(
            ball = ball,
            serveDirection = serveDirection
        )

        resetPaddles(
            leftPaddle = leftPaddle,
            rightPaddle = rightPaddle
        )
    }

    private fun resetBall(
        ball: Ball,
        serveDirection: Float
    ) {
        ball.setX(
            (config.worldWidth - ball.size) / 2f
        )

        ball.setY(
            (config.worldHeight - ball.size) / 2f
        )

        ball.setVelocityX(
            abs(config.ballSpeedX) * serveDirection
        )

        ball.setVelocityY(
            config.ballSpeedY
        )
    }

    private fun resetPaddles(
        leftPaddle: Paddle,
        rightPaddle: Paddle
    ) {
        val paddleY =
            (config.worldHeight - leftPaddle.height) / 2f

        leftPaddle.setY(
            targetY = paddleY,
            minY = config.fieldMargin,
            maxY = config.worldHeight -
                config.fieldMargin -
                leftPaddle.height
        )

        rightPaddle.setY(
            targetY = paddleY,
            minY = config.fieldMargin,
            maxY = config.worldHeight -
                config.fieldMargin -
                rightPaddle.height
        )
    }
}
