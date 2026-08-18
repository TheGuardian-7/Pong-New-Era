package com.pongnewera

import com.badlogic.gdx.Game
import com.pongnewera.screens.GameScreen

class PongNewEraGame : Game() {

    override fun create() {
        setScreen(GameScreen(this))
    }
}
