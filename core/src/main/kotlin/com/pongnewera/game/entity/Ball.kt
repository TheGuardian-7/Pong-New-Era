package com.pongnewera.game.entity

class Ball(
    val size: Float,
    initialX: Float,
    initialY: Float,
    initialVelocityX: Float,
    initialVelocityY: Float
) {

    var x: Float = initialX
        private set

    var y: Float = initialY
        private set

    var velocityX: Float = initialVelocityX
        private set

    var velocityY: Float = initialVelocityY
        private set

    fun setX(value: Float) {
        x = value
    }

    fun setY(value: Float) {
        y = value
    }

    fun setVelocityX(value: Float) {
        velocityX = value
    }

    fun setVelocityY(value: Float) {
        velocityY = value
    }
}
