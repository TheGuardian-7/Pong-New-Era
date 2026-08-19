package com.pongnewera.game

import kotlin.math.abs

class BallBoundsSystem {

    fun update(ball: Ball) {
        val topLimit =
            PongGame.WORLD_HEIGHT -
                PongGame.FIELD_MARGIN -
                10f -
                ball.size

        val bottomLimit =
            PongGame.FIELD_MARGIN + 10f

        if (ball.y >= topLimit) {
            ball.setY(topLimit)
            ball.setVelocityY(-abs(ball.velocityY))
        }

        if (ball.y <= bottomLimit) {
            ball.setY(bottomLimit)
            ball.setVelocityY(abs(ball.velocityY))
        }
    }
}
