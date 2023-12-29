package com.pixsterstudio.androidarckxml.session



public interface Session {

    var userSession: String

    var userId: String

    var user: String?

    val language: String



    fun clearSession()

    companion object {
        const val USER_SESSION = "token"
        const val USER_ID = "USER_ID"
        const val AUTHORIZATION = "Authorization"
        const val CONTENT_TYPE = "Content-Type"
        const val API_TOKEN = "Token 82c8ceb9ee9f65410550494f0ca882fed1fbbc27"
        const val CONTENT_AUDIO = "audio/wav"
    }
}
