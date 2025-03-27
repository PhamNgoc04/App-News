package com.example.appnews.data

import com.example.appnews.data.local.NewsEntity
import com.example.appnews.data.model.NewsItem
import com.example.appnews.data.remote.NewsDto

fun NewsDto.toNewsItem(): NewsItem {
    return NewsItem(
        title = title,
        description = description ?: "",
        imageUrl = urlToImage ?: "",
        publishedAt = publishedAt,
    )
}

fun NewsDto.toEntity(): NewsEntity {
    return NewsEntity(
        title = title,
        description = description ?: "",
        imageUrl = urlToImage ?: "",
        publishedAt = publishedAt,
    )
}
