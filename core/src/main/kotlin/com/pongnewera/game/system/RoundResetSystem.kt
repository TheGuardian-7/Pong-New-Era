package com.pongnewera.game.system

import com.pongnewera.game.Ball
import com.pongnewera.game.Paddle
import kotlin.math.abs

class RoundResetSystem {

    companion object {
        private const val BALL_SPEED_X = 300f
        private const val BALL_SPEED_Y = 180f
    }

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
            (WORLD_WIDTH - ball.size) / 2f
        )

        ball.setY(
            (WORLD_HEIGHT - ball.size) / 2f
        )

        ball.setVelocityX(
            abs(BALL_SPEED_X) * serveDirection
        )

        ball.setVelocityY(BALL_SPEED_Y)
    }

    private fun resetPaddles(
        leftPaddle: Paddle,
        rightPaddle: Paddle
    ) {
        val paddleY =
            (WORLD_HEIGHT - leftPaddle.height) / 2f

        leftPaddle.setY(
            targetY = paddleY,
            minY = FIELD_MARGIN,
            maxY = WORLD_HEIGHT -
                FIELD_MARGIN -
                leftPaddle.height
        )

        rightPaddle.setY(
            targetY = paddleY,
            minY = FIELD_MARGIN,
            maxY = WORLD_HEIGHT -
                FIELD_MARGIN -
                rightPaddle.height
        )
    }

    private companion object {
        const val WORLD_WIDTH = 800f
        const val WORLD_HEIGHT = 480f
        const val FIELD_MARGIN = 30f
    }
}
