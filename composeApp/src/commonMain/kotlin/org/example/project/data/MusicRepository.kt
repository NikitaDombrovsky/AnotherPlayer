package org.example.project.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header

class MusicRepository(private val client: HttpClient) {
    suspend fun getSongs(): List<Song> =
        client.get("https://myheihcbyastpymcpqiy.supabase.co/rest/v1/songs?select=*"){
            header("apikey", "sb_publishable_wbJ1IyeVAM-rBxhtWd-Gdg_LaXEN3FV")
            header("Authorization", "Bearer sb_publishable_wbJ1IyeVAM-rBxhtWd-Gdg_LaXEN3FV")
        }.body()
}
/*

curl 'https://myheihcbyastpymcpqiy.supabase.co/rest/v1/songs?select=*' \
-H "apikey: sb_publishable_wbJ1IyeVAM-rBxhtWd-Gdg_LaXEN3FV" \
-H "Authorization: Bearer sb_publishable_wbJ1IyeVAM-rBxhtWd-Gdg_LaXEN3FV"

 */