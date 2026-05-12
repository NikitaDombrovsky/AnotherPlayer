package org.example.project

import kotlinx.coroutines.CoroutineScope
import org.example.project.audio.PlayerController

expect fun createPlayerController(scope: CoroutineScope): PlayerController