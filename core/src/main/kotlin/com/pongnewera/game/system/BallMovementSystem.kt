package com.pongnewera.game.system

import com.pongnewera.game.entity.Ball

class BallMovementSystem {

    fun update(
        ball: Ball,
        delta: Float
    ) {
        ball.move(delta)
    }
}
