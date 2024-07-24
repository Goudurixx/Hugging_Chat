package com.goudurixx.huggingchat.core.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Model(
    val _id: String, //"621ffdc036468d709f174328"
    val id: String, // "albert/albert-base-v1"
    val likes: Int,//7
    val private: Boolean, //false
    val downloads: Int, //17412
    val tags: List<String>,// ["transformers","pytorch", ... "region:us"]
    val pipeline_tag: String? = null, //"fill-mask"
    val library_name: String? = null,//"transformers"
    val createdAt: String, //"2022-03-02T23:29:04.000Z"
    val modelId: String, //"albert/albert-base-v1"
)