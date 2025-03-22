package com.example.appnews.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String = "tesla",
        @Query("from") from: String = "2025-02-22",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "e573ba1bdd0c45199bb75715d827e713"
    ): NewsResponse
}
