package com.example.appnews.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.appnews.data.model.NewsItem
import com.example.appnews.viewmodel.NewsViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavController, viewModel: NewsViewModel = hiltViewModel()) {
    val newsList by viewModel.newsList.collectAsState()
    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // AppBar
        TopAppBar(
            title = { Text("News") },
            actions = {
                IconButton(onClick = { isSearching = !isSearching }) {
                    Icon(Icons.Default.Search, contentDescription = "Tìm kiếm")
                }
            }
        )

        // Thanh tìm kiếm
        if (isSearching) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Nhập từ khóa tìm kiếm...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                singleLine = true,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (searchQuery.isNotBlank()) {
                                viewModel.searchNews(searchQuery)
                            } else {
                                viewModel.fetchNews() // load lại toàn bộ tin nếu query rỗng
                            }
                        }
                    ) {
                        Icon(Icons.Default.Search, contentDescription = "Tìm")
                    }
                }
            )
        }

        Button(
            onClick = { navController.navigate("favorites") }
        ) {
            Text(text = "Favorites")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(newsList) { news ->
                NewsItemCard(news) {
                    navController.navigate(
                        "news_detail/${URLEncoder.encode(news.title, StandardCharsets.UTF_8.name())}/" +
                                "${URLEncoder.encode(news.description, StandardCharsets.UTF_8.name())}/" +
                                "${URLEncoder.encode(news.imageUrl, StandardCharsets.UTF_8.name())}"
                    )                }
            }
        }
    }
}


@Composable
fun NewsItemCard(news: NewsItem, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable {
                onClick(
                    //cần thêm hành động
                )
            } // Nhấn vào để điều hướng
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = rememberAsyncImagePainter(news.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = news.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = news.description,
                    fontSize = 14.sp,
                    maxLines = 2
                )
            }
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun NewScreenPreview(
    modifier: Modifier = Modifier
) {
    NewsItemCard(
        news = NewsItem(
            id = 1,
            title = "Title",
            description = "Description",
            imageUrl = "https://via.placeholder.com/150",
            publishedAt = "2021-09-01"
        ),
        onClick = {}
    )

    NewsItemCard(
        news = NewsItem(
            id = 1,
            title = "Title",
            description = "Description",
            imageUrl = "https://via.placeholder.com/150",
            publishedAt = "2021-09-01"
        ),
        onClick = {}
    )

    NewsItemCard(
        news = NewsItem(
            id = 1,
            title = "Title",
            description = "Description",
            imageUrl = "https://via.placeholder.com/150",
            publishedAt = "2021-09-01"
        ),
        onClick = {}
    )

    NewsItemCard(
        news = NewsItem(
            id = 1,
            title = "Title",
            description = "Description",
            imageUrl = "https://via.placeholder.com/150",
            publishedAt = "2021-09-01"
        ),
        onClick = {}
    )

    NewsItemCard(
        news = NewsItem(
            id = 1,
            title = "Title",
            description = "Description",
            imageUrl = "https://via.placeholder.com/150",
            publishedAt = "2021-09-01"
        ),
        onClick = {}
    )
}

