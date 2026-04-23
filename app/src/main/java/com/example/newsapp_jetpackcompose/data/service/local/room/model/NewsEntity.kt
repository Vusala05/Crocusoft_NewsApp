package com.example.newsapp_jetpackcompose.data.service.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey val url : String,
    val title : String,
    val imageUrl : String,
    val publishedAt : String,
    val author : String,
)
