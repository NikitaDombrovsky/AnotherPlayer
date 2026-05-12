package org.example.project.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.project.data.Song

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListScreen(
    songs: List<Song>,
    onSongClick: (Song) -> Unit
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
                    },
                    modifier = Modifier.clickable { onSongClick(song) }
                )

            }
        }
    }
}