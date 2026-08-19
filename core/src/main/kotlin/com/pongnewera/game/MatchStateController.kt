package com.pongnewera.game

class MatchStateController {

    var state: MatchState = MatchState.READY
        private set

    fun start() {
        if (state == MatchState.READY) {
            state = MatchState.PLAYING
        }
    }

    fun handlePointScored(hasWinner: Boolean) {
        state = if (hasWinner) {
            MatchState.GAME_OVER
        } else {
            MatchState.POINT_SCORED
        }
    }

    fun continueAfterPoint() {
        if (state == MatchState.POINT_SCORED) {
            state = MatchState.PLAYING
        }
    }

    fun reset() {
        state = MatchState.READY
    }
}
