package com.example.newsapp_jetpackcompose.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.newsapp_jetpackcompose.data.service.local.room.model.NewsEntity
import com.example.newsapp_jetpackcompose.data.service.remote.model.NewsModel
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import java.net.URLEncoder
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun NewsModel.toUiModel() : NewsUiModel = NewsUiModel(
     source = source?.name ?: "Unknown source",
     author = author ?: "Unknown author",
     title =  title ?: "",
     description = description ?: "",
     url = URLEncoder.encode(url ?:"","UTF-8"),
     urlToImage = urlToImage?:"",
     publishedAt = publishedAt?.formatDate()?:"",
     content = content?:""
)
fun NewsEntity.toUiModel() : NewsUiModel = NewsUiModel(
     author = author,
     title =  title,
     url = url,
     publishedAt = publishedAt,
     urlToImage = imageUrl
)
fun NewsUiModel.toEntity(): NewsEntity {
     return NewsEntity(
          url = url,
          title = title,
          imageUrl = urlToImage,
          publishedAt = publishedAt,
          author = author
     )
}



@RequiresApi(Build.VERSION_CODES.O)
fun String.formatDate(): String {
     return try {
          val parsedDate = ZonedDateTime.parse(this)

          val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())

          parsedDate.format(formatter)
     } catch (e: Exception) {
          this
     }
}