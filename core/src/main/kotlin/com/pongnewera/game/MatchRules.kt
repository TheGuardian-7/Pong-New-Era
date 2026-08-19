package com.pongnewera.game

class MatchRules(
    private val winningScore: Int = 5
) {

    fun hasWinner(score: Score): Boolean {
        return score.left >= winningScore ||
            score.right >= winningScore
    }

    fun determineScoringPlayer(ball: Ball): ScoringPlayer? {
        return when {
            ball.x + ball.size < 0f -> ScoringPlayer.RIGHT
            ball.x > PongGame.WORLD_WIDTH -> ScoringPlayer.LEFT
            else -> null
        }
    }
}

enum class ScoringPlayer {
    LEFT,
    RIGHT
}
