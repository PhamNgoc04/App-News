package com.example.appnews.data.repository

import com.example.appnews.data.local.NewsDao
import com.example.appnews.data.local.NewsEntity
import com.example.appnews.data.model.NewsItem
import com.example.appnews.data.remote.ApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {

    // Hàm lấy dữ liệu từ API và lưu vào Room
    //Chuyển đổi từ API sang dạng NewsEntity
    suspend fun getNewsFromApi(): List<NewsEntity> {
        val response = apiService.getNews() //response = NewsResponse
        val newsEntities = response.articles.map { article ->
            NewsEntity(
                title = article.title,
                description = article.description ?: "",
                imageUrl = article.urlToImage ?: "",
                publishedAt = article.publishedAt?.toString() ?: "Unknown Date"
            )
        }
        // Lưu dữ liệu vào Room
        newsDao.insertNews(newsEntities)
        return newsEntities
    }

    // Hàm lấy dữ liệu từ Room
    suspend fun getNewsFromDb(): List<NewsEntity> {
        return newsDao.getAllNews()
    }
}
