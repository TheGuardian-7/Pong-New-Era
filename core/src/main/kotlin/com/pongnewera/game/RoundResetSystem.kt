package com.pongnewera.game

class RoundResetSystem {

    fun reset(
        ball: Ball,
        leftPaddle: Paddle,
        rightPaddle: Paddle
    ) {
        val centerX =
            (PongGame.WORLD_WIDTH - ball.size) / 2f

        val centerY =
            (PongGame.WORLD_HEIGHT - ball.size) / 2f

        ball.setX(centerX)
        ball.setY(centerY)

        ball.setVelocityX(
            if (ball.velocityX >= 0f) {
                300f
            } else {
                -300f
            }
        )

        ball.setVelocityY(180f)

        val paddleY =
            (PongGame.WORLD_HEIGHT - PongGame.PADDLE_HEIGHT) / 2f

        leftPaddle.setY(
            targetY = paddleY,
            minY = PongGame.FIELD_MARGIN,
            maxY = PongGame.WORLD_HEIGHT -
                PongGame.FIELD_MARGIN -
                PongGame.PADDLE_HEIGHT
        )

        rightPaddle.setY(
            targetY = paddleY,
            minY = PongGame.FIELD_MARGIN,
            maxY = PongGame.WORLD_HEIGHT -
                PongGame.FIELD_MARGIN -
                PongGame.PADDLE_HEIGHT
        )
    }
}
