package com.example.appnews.data.remote

import com.example.appnews.data.model.NewsItem

//Hoạt động như một lớp trung gian hay một "container tạm thời" để lưu trữ dữ liệu trước khi nó được hiển thị.
//Dữ liệu này được giữ trong NewsResponse chỉ trong thời gian ngắn, ngay khi API trả về.
//Lớp dùng để ánh xạ dữ liệu từ API sang lớp NewsResponse.
data class NewsResponse(
    val articles: List<NewsDto>
)

data class NewsDto(
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String? // Thêm thuộc tính này
)

//
//fun NewsDto.toNewsItem(): NewsItem {
//    return NewsItem(
//        title = this.title ?: "",
//        description = this.description ?: "",
//        urlToImage = this.urlToImage ?: "",
//        publishedAt = this.publishedAt ?: ""
//    )
//}
