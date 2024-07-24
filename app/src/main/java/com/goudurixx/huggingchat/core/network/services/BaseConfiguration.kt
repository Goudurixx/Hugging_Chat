package com.goudurixx.huggingchat.core.network.services

import android.util.Log
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpLogger : Logger {
    override fun log(message: String) {
        Log.d("HttpLogger", message)
    }
}

fun HttpClientConfig<*>.default() {
    install(HttpTimeout) {
        connectTimeoutMillis = 10000
        requestTimeoutMillis = 10000
        socketTimeoutMillis = 10000
    }

    install(Logging) {
        logger = HttpLogger()
        level = LogLevel.BODY //TODO remove log from release version
    }

    install(ContentNegotiation){
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }
}