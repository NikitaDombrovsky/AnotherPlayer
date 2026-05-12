package org.example.project.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val id : Int,
    val title: String,
    @SerialName("audio_url") val audioUrl: String,
    @SerialName("duration_seconds") val durationSeconds: Int
) {

}
