package com.pongnewera.game.entity

class Paddle(
    val x: Float,
    val width: Float,
    val height: Float,
    initialY: Float
) {

    var y: Float = initialY
        private set

    fun setY(
        targetY: Float,
        minY: Float,
        maxY: Float
    ) {
        y = targetY.coerceIn(minY, maxY)
    }
}
