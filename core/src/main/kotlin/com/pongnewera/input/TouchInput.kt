package com.pongnewera.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.viewport.Viewport

class TouchInput(
    private val viewport: Viewport,
    private val worldWidth: Float
) {

    private val touchPosition = Vector3()

    fun read(): PlayerInput {
        var leftPaddleY: Float? = null
        var rightPaddleY: Float? = null

        for (pointer in 0 until 2) {
            if (!Gdx.input.isTouched(pointer)) {
                continue
            }

            touchPosition.set(
                Gdx.input.getX(pointer).toFloat(),
                Gdx.input.getY(pointer).toFloat(),
                0f
            )

            viewport.unproject(touchPosition)

            if (touchPosition.x < worldWidth / 2f) {
                leftPaddleY = touchPosition.y
            } else {
                rightPaddleY = touchPosition.y
            }
        }

        return PlayerInput(
            leftPaddleY = leftPaddleY,
            rightPaddleY = rightPaddleY
        )
    }
}
