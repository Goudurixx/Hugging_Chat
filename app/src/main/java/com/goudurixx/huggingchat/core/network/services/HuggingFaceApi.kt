package com.goudurixx.huggingchat.core.network.services

import com.goudurixx.huggingchat.core.network.models.Model
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header

class HuggingFaceApi {

    private val client = HttpClient {
        default()
        defaultRequest {
            url("https://huggingface.co/api/")
            header("Accept", "*/*")
//            header("Authorization", $KEY)
        }
    }

    suspend fun getModels() : List<Model> {
        return client.get("models").body()
    }

}