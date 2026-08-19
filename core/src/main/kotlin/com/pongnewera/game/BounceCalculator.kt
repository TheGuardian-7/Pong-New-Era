package com.pongnewera.game

import kotlin.math.cos
import kotlin.math.sin

class BounceCalculator {

    companion object {
        private const val MAX_BOUNCE_ANGLE_DEGREES = 60f
    }

    fun calculateVelocity(
        ball: Ball,
        paddle: Paddle
    ): Pair<Float, Float> {

        val ballCenterY = ball.y + ball.size / 2f
        val paddleCenterY = paddle.y + paddle.height / 2f

        val relativeIntersection =
            ((ballCenterY - paddleCenterY) / (paddle.height / 2f))
                .coerceIn(-1f, 1f)

        val angleDegrees =
            relativeIntersection * MAX_BOUNCE_ANGLE_DEGREES

        val angleRadians =
            Math.toRadians(angleDegrees.toDouble())

        val speed =
            kotlin.math.sqrt(
                ball.velocityX * ball.velocityX +
                    ball.velocityY * ball.velocityY
            )

        val directionX =
            if (ball.velocityX < 0f) 1f else -1f

        val velocityX =
            directionX * speed * cos(angleRadians).toFloat()

        val velocityY =
            speed * sin(angleRadians).toFloat()

        return velocityX to velocityY
    }
}
