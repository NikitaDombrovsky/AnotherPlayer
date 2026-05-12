package org.example.project.audio

import kotlinx.coroutines.flow.StateFlow

interface PlayerController {
    val state: StateFlow<PlayerState>

    fun play(url: String)

    fun pause()

    fun resume()

    fun searchTo(positionMs: Long)
}
