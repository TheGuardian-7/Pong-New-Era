package com.pongnewera.game

import com.pongnewera.game.system.BallBoundsSystem
import com.pongnewera.game.system.BallMovementSystem
import com.pongnewera.game.system.CollisionSystem
import com.pongnewera.game.system.PaddleMovementSystem
import com.pongnewera.game.system.RoundResetSystem
import com.pongnewera.game.system.ScoringSystem
import com.pongnewera.input.PlayerInput

class PongGame {

    companion object {
        const val WORLD_WIDTH = 800f
        const val WORLD_HEIGHT = 480f

        const val FIELD_MARGIN = 30f

        const val PADDLE_WIDTH = 12f
        const val PADDLE_HEIGHT = 80f

        const val BALL_SIZE = 12f

        private const val INITIAL_BALL_VELOCITY_X = 300f
        private const val INITIAL_BALL_VELOCITY_Y = 180f
    }

    val leftPaddle = Paddle(
        x = 40f,
        width = PADDLE_WIDTH,
        height = PADDLE_HEIGHT,
        initialY = (WORLD_HEIGHT - PADDLE_HEIGHT) / 2f
    )

    val rightPaddle = Paddle(
        x = WORLD_WIDTH - 40f - PADDLE_WIDTH,
        width = PADDLE_WIDTH,
        height = PADDLE_HEIGHT,
        initialY = (WORLD_HEIGHT - PADDLE_HEIGHT) / 2f
    )

    val ball = Ball(
        size = BALL_SIZE,
        initialX = (WORLD_WIDTH - BALL_SIZE) / 2f,
        initialY = (WORLD_HEIGHT - BALL_SIZE) / 2f,
        initialVelocityX = INITIAL_BALL_VELOCITY_X,
        initialVelocityY = INITIAL_BALL_VELOCITY_Y
    )

    val score = Score()

    private val matchStateController = MatchStateController()

    private val paddleMovementSystem = PaddleMovementSystem()
    private val ballMovementSystem = BallMovementSystem()
    private val collisionSystem = CollisionSystem(
        bounceCalculator = BounceCalculator()
    )
    private val ballBoundsSystem = BallBoundsSystem()
    private val scoringSystem = ScoringSystem(
        matchRules = MatchRules()
    )
    private val roundResetSystem = RoundResetSystem()

    val matchState: MatchState
        get() = matchStateController.state

    fun start() {
        matchStateController.start()
    }

    fun continueAfterPoint() {
        if (matchState != MatchState.POINT_SCORED) {
            return
        }

        roundResetSystem.reset(
            ball = ball,
            leftPaddle = leftPaddle,
            rightPaddle = rightPaddle,
            serveDirection = determineServeDirection()
        )

        matchStateController.continueAfterPoint()
    }

    fun update(
        delta: Float,
        input: PlayerInput
    ) {
        if (matchState != MatchState.PLAYING) {
            return
        }

        updatePaddles(input)
        updateBall(delta)
        resolveBallCollisions()
        resolveBallBounds()
        processScoring()
    }

    private fun updatePaddles(input: PlayerInput) {
        paddleMovementSystem.update(
            leftPaddle = leftPaddle,
            rightPaddle = rightPaddle,
            input = input
        )
    }

    private fun updateBall(delta: Float) {
        ballMovementSystem.update(
            ball = ball,
            delta = delta
        )
    }

    private fun resolveBallCollisions() {
        collisionSystem.update(
            ball = ball,
            leftPaddle = leftPaddle,
            rightPaddle = rightPaddle
        )
    }

    private fun resolveBallBounds() {
        ballBoundsSystem.update(ball)
    }

    private fun processScoring() {
        val scoringResult = scoringSystem.update(
            ball = ball,
            score = score
        )

        if (scoringResult == ScoringResult.NONE) {
            return
        }

        matchStateController.handlePointScored(
            hasWinner = determineWinner()
        )
    }

    private fun determineWinner(): Boolean {
        return score.left >= WINNING_SCORE ||
            score.right >= WINNING_SCORE
    }

    private fun determineServeDirection(): Float {
        return when {
            score.left > score.right -> -1f
            score.right > score.left -> 1f
            else -> 1f
        }
    }

    private companion object {
        const val WINNING_SCORE = 5
    }
}
