package org.example.project

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.audio.PlayerController
import org.example.project.audio.PlayerState


actual fun createPlayerController(scope: CoroutineScope): PlayerController = JsAudioPlayer(scope)


class JsAudioPlayer(private val scope: CoroutineScope) : PlayerController {

    private val mediaPlayer: dynamic = js("new Audio()")


    private val _state = MutableStateFlow(PlayerState())
    override val state: StateFlow<PlayerState> = _state.asStateFlow()


    private var trackingJob : Job? = null

    override fun play(url: String) {
        trackingJob?.cancel()

        mediaPlayer.src = url
        mediaPlayer.load()

        trackingJob = scope.launch {

        }



        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun searchTo(positionMs: Long) {
        TODO("Not yet implemented")
    }
}