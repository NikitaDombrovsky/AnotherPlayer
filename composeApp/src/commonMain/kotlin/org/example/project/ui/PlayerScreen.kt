package org.example.project.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Сейчас Играет")},
                navigationIcon = {
                    IconButton(
                        onClick = {/*TODO*/}
                    ) {
                        Icon(

                        )
                    }
                }
            )
        }
    ) {

    }
}