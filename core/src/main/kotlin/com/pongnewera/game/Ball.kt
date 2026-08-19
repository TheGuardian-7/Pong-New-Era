package com.pongnewera.game

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

    fun move(delta: Float) {
        x += velocityX * delta
        y += velocityY * delta
    }

    fun setX(newX: Float) {
        x = newX
    }

    fun setY(newY: Float) {
        y = newY
    }

    fun setVelocityX(newVelocityX: Float) {
        velocityX = newVelocityX
    }

    fun setVelocityY(newVelocityY: Float) {
        velocityY = newVelocityY
    }
}
