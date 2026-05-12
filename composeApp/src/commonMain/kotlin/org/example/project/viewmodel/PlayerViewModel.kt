package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.audio.PlayerState
import org.example.project.createPlayerController
import org.example.project.data.MusicRepository
import org.example.project.data.Song
import org.example.project.network.createHttpClient

class PlayerViewModel : ViewModel() {
    private val client = createHttpClient()
    private val repository = MusicRepository(client)

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val song: StateFlow<List<Song>> = _songs.asStateFlow()

    val audioPlayer = createPlayerController()

    val playerState: StateFlow<PlayerState> = audioPlayer.state


    val _currentSong = MutableStateFlow<Song?>(null)
    val currentSong: StateFlow<Song?> = _currentSong.asStateFlow()

    init {
        loadSongs()
        // TODO Тест
        //audioPlayer.play("https://myheihcbyastpymcpqiy.supabase.co/storage/v1/object/public/audio/music2.mp3")
    }

    fun selectSong(song: Song)
    {
        _currentSong.value = song
        audioPlayer.play(song.audioUrl)
    }

    private fun loadSongs(){
        viewModelScope.launch {
            _songs.value = repository.getSongs()
        }
    }

}