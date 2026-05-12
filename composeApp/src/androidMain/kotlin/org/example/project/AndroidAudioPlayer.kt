package org.example.project

import android.media.MediaPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.example.project.audio.PlayerController
import org.example.project.audio.PlayerState


actual fun createPlayerController(scope: CoroutineScope): PlayerController = AndroidAudioPlayer(scope)


class AndroidAudioPlayer(
    private val scope: CoroutineScope
) : PlayerController {

    private var mediaPlayer: MediaPlayer? = null

    private val _state = MutableStateFlow(PlayerState())
    override val state: StateFlow<PlayerState> = _state.asStateFlow()

    private var trackingJob: Job? = null

    override fun play(url: String) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener { song ->
                song.start()
                startTracking()
                _state.update {
                    it.copy(isLoading = false, isPlaying = true, durationMs = song.duration.toLong())
                }
            }
        }
        print(mediaPlayer?.getSelectedTrack(2))
    }

    override fun pause() {
        mediaPlayer?.pause()
        trackingJob?.cancel()
        _state.update { it.copy(isPlaying = false) }
    }

    override fun resume() {
        mediaPlayer?.start()
        startTracking()
        _state.update { it.copy(isPlaying = true) }
    }

    override fun searchTo(positionMs: Long) {
        mediaPlayer?.seekTo(positionMs.toInt())
        _state.update { it.copy(currentPositionMs = positionMs) }
    }

    private fun startTracking(){
        trackingJob?.cancel()
        trackingJob = scope.launch {
            while (isActive){
                val mp = mediaPlayer ?: break
                if (mp.isPlaying){
                    _state.update { it.copy(currentPositionMs = mp.currentPosition.toLong()) }
                }
                delay(500)
            }

        }
    }

}