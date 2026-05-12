package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.data.Song
import org.example.project.viewmodel.PlayerViewModel
import org.jetbrains.compose.resources.painterResource

import player.composeapp.generated.resources.Res
import player.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel: PlayerViewModel = viewModel { PlayerViewModel() }

        val songs by viewModel.song.collectAsState()

        println(songs.toString())
        SongListScreen(songs)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListScreen(
    songs: List<Song>
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Песни Валерия Меладзе") }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = it
        ){
            items(items=songs, key = {it.id}){ song ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = song.title
                        )
                    }
                )

            }
        }
    }
}