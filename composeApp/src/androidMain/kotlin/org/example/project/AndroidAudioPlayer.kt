package org.example.project

import android.media.MediaPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            setOnPreparedListener {
                it.start()
            }
        }
        print(mediaPlayer?.getSelectedTrack(2))
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