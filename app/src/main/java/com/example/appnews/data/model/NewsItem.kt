package com.example.appnews.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class NewsItem(
    val id: Int = 0,
    val title: String,
    val description: String,
    val imageUrl: String,
    val publishedAt: String? // Ngày xuất bản tin tức
) : Serializable
