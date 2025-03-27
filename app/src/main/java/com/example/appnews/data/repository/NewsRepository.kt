package com.example.appnews.data.repository

import com.example.appnews.data.local.NewsDao
import com.example.appnews.data.local.NewsEntity
import com.example.appnews.data.model.NewsItem
import com.example.appnews.data.remote.ApiService
import com.example.appnews.data.remote.NewsDto
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
                publishedAt = article.publishedAt?.toString() ?: "Unknown Date",
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

    // Lấy danh sách yêu thích từ Room
    suspend fun getFavoriteNews(): List<NewsEntity> {
        return newsDao.getAllNews()
    }

    // Hàm mới để trả về dữ liệu dạng DTO (dùng cho searchNews)
    suspend fun getNewsDtoFromApi(query: String): List<NewsDto> {
        val response = apiService.getNews(query = query)
        return response.articles
    }

    //Hàm tìm kiếm tin tức từ API
    suspend fun searchNewsFromApi(query: String): List<NewsItem> {
        val response = apiService.getNews(query = query)
        return response.articles.map { article ->
            NewsItem(
                title = article.title ?: "No Title",
                description = article.description ?: "",
                imageUrl = article.urlToImage ?: "",
                publishedAt = article.publishedAt ?: ""
            )
        }
    }


    suspend fun getNewsByQuery(query: String): List<NewsItem> {
        val response = apiService.getNews() // hoặc apiService.searchNews(query) nếu API có hỗ trợ
        return response.articles
            .filter { it.title.contains(query, ignoreCase = true) || it.description?.contains(query, ignoreCase = true) == true }
            .map { dto ->
                NewsItem(
                    id = 0,
                    title = dto.title ?: "",
                    description = dto.description ?: "",
                    imageUrl = dto.urlToImage ?: "",
                    publishedAt = dto.publishedAt ?: ""
                )
            }
    }


}
