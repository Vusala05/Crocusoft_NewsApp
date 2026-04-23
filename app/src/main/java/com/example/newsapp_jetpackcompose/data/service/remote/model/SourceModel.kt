package com.example.newsapp_jetpackcompose.data.service.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SourceModel(
    val id: String?=null,
    val name: String?=null
)