package com.example.appnews.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.appnews.data.local.NewsDao
import com.example.appnews.data.local.NewsDatabase
import com.example.appnews.data.remote.ApiService
import com.example.appnews.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(application: Application): NewsDatabase =
        Room.databaseBuilder(application, NewsDatabase::class.java, "news_db")
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun provideNewsDao(database: NewsDatabase) = database.newsDao()

    @Provides
    @Singleton
    fun provideNewsRepository(apiService: ApiService, newsDao: NewsDao) =
        NewsRepository(apiService, newsDao)
}