package com.pongnewera.game.system

import com.pongnewera.game.entity.Ball
import com.pongnewera.game.GameConfig
import kotlin.math.abs

class BallBoundsSystem(
    private val config: GameConfig
) {

    fun update(ball: Ball) {
        val topLimit =
            config.worldHeight -
                config.fieldMargin -
                ball.size

        val bottomLimit =
            config.fieldMargin

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
