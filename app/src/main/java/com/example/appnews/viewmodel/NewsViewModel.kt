package com.example.appnews.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.appnews.data.model.NewsItem
import com.example.appnews.domain.GetNewsUseCase

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _newsList = MutableStateFlow<List<NewsItem>>(emptyList())
    val newsList: StateFlow<List<NewsItem>> = _newsList

    init {
        fetchNews()
    }

    //DÙng internal để NewsScreen có thể gọi hàm này
    internal fun fetchNews() {
        viewModelScope.launch {
            _newsList.value = getNewsUseCase()
        }
    }

    //Thêm danh sách yêu thích
    private val _favoriteNews = mutableStateListOf<NewsItem>()
    val favoriteNews: List<NewsItem> get() = _favoriteNews

    fun addToFavorite(news: NewsItem) {
        if (!_favoriteNews.contains(news)) {
            _favoriteNews.add(news)
        }
    }


    fun searchNews(query: String) {
        viewModelScope.launch {
            try {
                val searchResults = getNewsUseCase() // <-- dòng này sẽ hết báo đỏ nếu bạn đã sửa đúng như trên
                _newsList.value = searchResults
            } catch (e: Exception) {
                Log.e("NewsViewModel", "Search failed: ${e.message}")
            }
        }
    }

}