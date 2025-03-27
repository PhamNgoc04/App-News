package com.example.appnews.domain

import com.example.appnews.data.local.NewsEntity
import com.example.appnews.data.model.NewsItem
import com.example.appnews.data.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    // Hàm này sẽ được gọi khi cần dữ liệu từ UseCase
    suspend operator fun invoke(): List<NewsItem> {
        val newsEntities = repository.getNewsFromDb()
        return newsEntities.map { it.toNewsItem() }
    }

    // Chuyển đổi từ NewsEntity sang NewsItem (Định dạng UI) dùng để hiển thị dữ liệu trên UI
    private fun NewsEntity.toNewsItem(): NewsItem {
        return NewsItem(
            id = this.id,
            title = this.title,
            description = this.description,
            imageUrl = this.imageUrl.ifEmpty { "https://via.placeholder.com/150" },
            publishedAt = this.publishedAt
        )
    }
}
