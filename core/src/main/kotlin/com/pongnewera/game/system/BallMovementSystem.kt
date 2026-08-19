package com.pongnewera.game.system

import com.pongnewera.game.Ball

class BallMovementSystem {

    fun update(
        ball: Ball,
        delta: Float
    ) {
        ball.move(delta)
    }
}
