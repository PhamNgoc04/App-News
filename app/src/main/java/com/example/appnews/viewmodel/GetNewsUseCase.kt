package com.example.appnews.viewmodel

import com.example.appnews.data.local.NewsEntity
import com.example.appnews.data.model.NewsItem
import com.example.appnews.data.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    // Hàm mặc định không có query
    suspend operator fun invoke(): List<NewsEntity> {
        return repository.getNewsFromApi() // hoặc từ DB nếu bạn muốn
    }

    // Hàm thêm để tìm kiếm với query
    suspend operator fun invoke(query: String): List<NewsItem> {
        return repository.searchNewsFromApi(query)
    }
}
