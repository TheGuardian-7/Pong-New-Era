package com.pongnewera.game.system

import com.pongnewera.game.Ball
import com.pongnewera.game.MatchRules
import com.pongnewera.game.Score
import com.pongnewera.game.ScoringPlayer
import com.pongnewera.game.ScoringResult

class ScoringSystem(
    private val matchRules: MatchRules
) {

    fun update(
        ball: Ball,
        score: Score
    ): ScoringResult {
        return when (matchRules.determineScoringPlayer(ball)) {
            ScoringPlayer.LEFT -> {
                score.addLeftPoint()
                ScoringResult.LEFT_SCORED
            }

            ScoringPlayer.RIGHT -> {
                score.addRightPoint()
                ScoringResult.RIGHT_SCORED
            }

            null -> ScoringResult.NONE
        }
    }
}
