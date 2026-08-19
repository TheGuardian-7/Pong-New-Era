package com.pongnewera.game

class CollisionSystem(
    private val bounceCalculator: BounceCalculator
) {

    fun update(
        ball: Ball,
        leftPaddle: Paddle,
        rightPaddle: Paddle
    ) {
        if (ball.velocityX < 0f && intersects(ball, leftPaddle)) {
            val velocity = bounceCalculator.calculateVelocity(
                ball = ball,
                paddle = leftPaddle
            )

            ball.setX(leftPaddle.x + leftPaddle.width)
            ball.setVelocityX(velocity.first)
            ball.setVelocityY(velocity.second)
        }

        if (ball.velocityX > 0f && intersects(ball, rightPaddle)) {
            val velocity = bounceCalculator.calculateVelocity(
                ball = ball,
                paddle = rightPaddle
            )

            ball.setX(rightPaddle.x - ball.size)
            ball.setVelocityX(velocity.first)
            ball.setVelocityY(velocity.second)
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
