package org.example.project

import android.media.MediaPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.audio.PlayerController
import org.example.project.audio.PlayerState


actual fun createPlayerController(): PlayerController = AndroidAudioPlayer()


class AndroidAudioPlayer : PlayerController {

    private var mediaPlayer: MediaPlayer? = null

    private val _state = MutableStateFlow(PlayerState())
    override val state: StateFlow<PlayerState> = _state.asStateFlow()

    override fun play(url: String) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener { song ->
                song.start()
                _state.update {
                    it.copy(isLoading = false, isPlaying = true, durationMs = song.duration.toLong())
                }
            }
        }
        print(mediaPlayer?.getSelectedTrack(2))
    }

    override fun pause() {
        mediaPlayer?.pause()
        _state.update { it.copy(isPlaying = false) }
    }

    override fun resume() {
        mediaPlayer?.start()
        _state.update { it.copy(isPlaying = true) }
    }

    override fun searchTo(positionMs: Long) {
        TODO("Not yet implemented")
    }

}