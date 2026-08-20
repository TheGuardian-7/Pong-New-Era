package com.pongnewera.game

class MatchRules(
    private val winningScore: Int,
    private val worldWidth: Float
) {

    fun hasWinner(score: Score): Boolean {
        return score.left >= winningScore ||
            score.right >= winningScore
    }

    fun determineScoringPlayer(ball: Ball): ScoringPlayer? {
        return when {
            ball.x + ball.size < 0f -> ScoringPlayer.RIGHT
            ball.x > worldWidth -> ScoringPlayer.LEFT
            else -> null
        }
    }
}

enum class ScoringPlayer {
    LEFT,
    RIGHT
}
