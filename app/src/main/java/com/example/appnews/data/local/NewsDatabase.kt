package com.example.appnews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [NewsEntity::class], version = 2, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Nếu bạn vừa thêm cột "publishedAt", hãy cập nhật schema bằng lệnh SQL sau:
            database.execSQL("ALTER TABLE news ADD COLUMN publishedAt TEXT DEFAULT 'Unknown Date'")
        }
    }



}