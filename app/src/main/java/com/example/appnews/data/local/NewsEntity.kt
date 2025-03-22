package com.example.appnews.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val imageUrl: String,
    val publishedAt: String? // Đảm bảo có thuộc tính này
) : Serializable

/*
Vai trò: Đây là lớp Entity dùng để lưu dữ liệu vào Room Database.
Dữ liệu từ API cần được lưu vào Room Database để có thể truy xuất khi không có mạng.
 */