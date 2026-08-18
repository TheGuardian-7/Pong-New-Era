package com.pongnewera.game

class CollisionSystem {

    fun update(
        ball: Ball,
        leftPaddle: Paddle,
        rightPaddle: Paddle
    ) {
        if (ball.velocityX < 0f && intersects(ball, leftPaddle)) {
            ball.setX(leftPaddle.x + leftPaddle.width)
            ball.setVelocityX(-ball.velocityX)
        }

        if (ball.velocityX > 0f && intersects(ball, rightPaddle)) {
            ball.setX(rightPaddle.x - ball.size)
            ball.setVelocityX(-ball.velocityX)
        }
    }

    private fun intersects(
        ball: Ball,
        paddle: Paddle
    ): Boolean {
        return ball.x < paddle.x + paddle.width &&
            ball.x + ball.size > paddle.x &&
            ball.y < paddle.y + paddle.height &&
            ball.y + ball.size > paddle.y
    }
}
