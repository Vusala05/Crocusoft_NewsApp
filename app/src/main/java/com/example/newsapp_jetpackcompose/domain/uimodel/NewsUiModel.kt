package com.example.newsapp_jetpackcompose.domain.uimodel


data class NewsUiModel(
    val source: String = "",
    val author: String= "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
) {
    companion object {
        val mock = NewsUiModel(
            source = "Reuters",
            author = "Jennifer Wars",
            title = "Factbox: Who is still buying Russian crude oil?",
            description = "Australia, Britain, Canada and the United States have imposed outright bans on Russian oil purchases following Moscow's invasion of Ukraine...",
            url = "https://example.com/news/1",
            urlToImage = "https://images.unsplash.com/photo-1581091226825-a6a2a5aee158",
            publishedAt = "2026-04-20T05:22:58Z",
            content = "(Reuters) - Australia, Britain, Canada and the United States have imposed outright bans on Russian oil purchases..."
        )

        val empty = NewsUiModel(
            source = "",
            author = "",
            title = "",
            description = "",
            url = "",
            urlToImage = "",
            publishedAt = "",
            content = ""
        )
    }
}