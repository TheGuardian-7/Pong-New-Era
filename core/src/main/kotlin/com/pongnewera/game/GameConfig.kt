package com.pongnewera.game

data class GameConfig(
    val worldWidth: Float = 800f,
    val worldHeight: Float = 480f,

    val fieldMargin: Float = 30f,

    val paddleWidth: Float = 12f,
    val paddleHeight: Float = 80f,

    val ballSize: Float = 12f,

    val ballSpeedX: Float = 300f,
    val ballSpeedY: Float = 180f,

    val winningScore: Int = 5
)
