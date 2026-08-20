package com.pongnewera.game.entity

class Score {

    var left: Int = 0
        private set

    var right: Int = 0
        private set

    fun addLeftPoint() {
        left++
    }

    fun addRightPoint() {
        right++
    }

    fun reset() {
        left = 0
        right = 0
    }
}
