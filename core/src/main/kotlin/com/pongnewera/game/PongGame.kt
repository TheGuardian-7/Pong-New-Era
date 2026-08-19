package com.pongnewera.game

import com.pongnewera.input.PlayerInput

class PongGame {

    companion object {
        const val WORLD_WIDTH = 800f
        const val WORLD_HEIGHT = 480f

        const val FIELD_MARGIN = 30f

        const val PADDLE_WIDTH = 12f
        const val PADDLE_HEIGHT = 80f

        const val BALL_SIZE = 12f

        private const val BALL_SPEED_X = 300f
        private const val BALL_SPEED_Y = 180f
    }

    val leftPaddle = Paddle(
        x = 40f,
        width = PADDLE_WIDTH,
        height = PADDLE_HEIGHT,
        initialY = (WORLD_HEIGHT - PADDLE_HEIGHT) / 2f
    )

    val rightPaddle = Paddle(
        x = WORLD_WIDTH - 40f - PADDLE_WIDTH,
        width = PADDLE_WIDTH,
        height = PADDLE_HEIGHT,
        initialY = (WORLD_HEIGHT - PADDLE_HEIGHT) / 2f
    )

    val ball = Ball(
        size = BALL_SIZE,
        initialX = (WORLD_WIDTH - BALL_SIZE) / 2f,
        initialY = (WORLD_HEIGHT - BALL_SIZE) / 2f,
        initialVelocityX = BALL_SPEED_X,
        initialVelocityY = BALL_SPEED_Y
    )

    private val bounceCalculator = BounceCalculator()

    private val collisionSystem = CollisionSystem(
        bounceCalculator = bounceCalculator
    )

    private val ballBoundsSystem = BallBoundsSystem()

    fun update(
        delta: Float,
        input: PlayerInput
    ) {
        updatePaddles(input)

        ball.update(delta)

        collisionSystem.update(
            ball = ball,
            leftPaddle = leftPaddle,
            rightPaddle = rightPaddle
        )

        ballBoundsSystem.update(ball)
    }

    private fun updatePaddles(input: PlayerInput) {
        input.leftPaddleY?.let {
            leftPaddle.setY(
                targetY = it - PADDLE_HEIGHT / 2f,
                minY = FIELD_MARGIN,
                maxY = WORLD_HEIGHT -
                    FIELD_MARGIN -
                    PADDLE_HEIGHT
            )
        }

        input.rightPaddleY?.let {
            rightPaddle.setY(
                targetY = it - PADDLE_HEIGHT / 2f,
                minY = FIELD_MARGIN,
                maxY = WORLD_HEIGHT -
                    FIELD_MARGIN -
                    PADDLE_HEIGHT
            )
        }
    }
}
