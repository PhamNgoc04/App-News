package com.example.appnews.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    // Thêm danh sách tin tức vào cơ sở dữ liệu
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsList: List<NewsEntity>)

    // Lấy tất cả tin đã lưu
    @Query("SELECT * FROM news")
    suspend fun getAllNews(): List<NewsEntity>


    ///Các hàm ở dưới chưa được triển khai thực hiện

    // Thêm một tin yêu thích
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(news: NewsEntity)

    // Xóa một tin khỏi danh sách yêu thích
    @Delete
    suspend fun removeFromFavorites(news: NewsEntity)

    // Kiểm tra xem một tin đã được yêu thích chưa (dựa theo tiêu đề)
    @Query("SELECT EXISTS(SELECT 1 FROM news WHERE title = :title)")
    suspend fun isFavorite(title: String): Boolean


}