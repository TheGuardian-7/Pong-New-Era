package com.pongnewera.game

import com.pongnewera.game.system.BallBoundsSystem
import com.pongnewera.game.system.BallMovementSystem
import com.pongnewera.game.system.CollisionSystem
import com.pongnewera.game.system.PaddleMovementSystem
import com.pongnewera.game.system.RoundResetSystem
import com.pongnewera.game.system.ScoringSystem
import com.pongnewera.input.PlayerInput

class PongGame(
    private val config: GameConfig = GameConfig()
) {

    val leftPaddle = Paddle(
        x = 40f,
        width = config.paddleWidth,
        height = config.paddleHeight,
        initialY = centeredPaddleY()
    )

    val rightPaddle = Paddle(
        x = config.worldWidth - 40f - config.paddleWidth,
        width = config.paddleWidth,
        height = config.paddleHeight,
        initialY = centeredPaddleY()
    )

    val ball = Ball(
        size = config.ballSize,
        initialX = centeredBallX(),
        initialY = centeredBallY(),
        initialVelocityX = config.ballSpeedX,
        initialVelocityY = config.ballSpeedY
    )

    val score = Score()

    private val matchStateController = MatchStateController()

    private val paddleMovementSystem = PaddleMovementSystem(
        config = config
    )

    private val ballMovementSystem = BallMovementSystem()

    private val collisionSystem = CollisionSystem(
        bounceCalculator = BounceCalculator()
    )

    private val ballBoundsSystem = BallBoundsSystem(
        config = config
    )

    private val scoringSystem = ScoringSystem(
        matchRules = MatchRules(
            winningScore = config.winningScore,
            worldWidth = config.worldWidth
        )
    )

    private val roundResetSystem = RoundResetSystem(
        config = config
    )

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

        paddleMovementSystem.update(
            leftPaddle = leftPaddle,
            rightPaddle = rightPaddle,
            input = input
        )

        ballMovementSystem.update(
            ball = ball,
            delta = delta
        )

        collisionSystem.update(
            ball = ball,
            leftPaddle = leftPaddle,
            rightPaddle = rightPaddle
        )

        ballBoundsSystem.update(ball)

        val scoringResult = scoringSystem.update(
            ball = ball,
            score = score
        )

        if (scoringResult != ScoringResult.NONE) {
            matchStateController.handlePointScored(
                hasWinner = score.left >= config.winningScore ||
                    score.right >= config.winningScore
            )
        }
    }

    private fun determineServeDirection(): Float {
        return when {
            score.left > score.right -> -1f
            score.right > score.left -> 1f
            else -> 1f
        }
    }

    private fun centeredPaddleY(): Float {
        return (config.worldHeight - config.paddleHeight) / 2f
    }

    private fun centeredBallX(): Float {
        return (config.worldWidth - config.ballSize) / 2f
    }

    private fun centeredBallY(): Float {
        return (config.worldHeight - config.ballSize) / 2f
    }
}
