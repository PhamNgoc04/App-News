package com.example.appnews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appnews.data.model.NewsItem
import com.example.appnews.ui.screens.FavoriteScreen
import com.example.appnews.ui.screens.NewsDetailScreen
import com.example.appnews.ui.screens.NewsScreen
import com.example.appnews.viewmodel.NewsViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation(
    navController: NavHostController,
    newsViewModel: NewsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "news_list"
    ) {
        composable("news_list") {
            NewsScreen(navController)
        }

        composable(
            "news_detail/{title}/{description}/{imageUrl}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("imageUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = decodeArg(backStackEntry.arguments?.getString("title"))
            val description = decodeArg(backStackEntry.arguments?.getString("description"))
            val imageUrl = decodeArg(backStackEntry.arguments?.getString("imageUrl"))

            val newsItem = NewsItem(id = 0, title = title, description = description, imageUrl = imageUrl, publishedAt = "N/A")
            NewsDetailScreen(navController, newsItem, newsViewModel)
        }

        composable("favorites") {
            FavoriteScreen(navController, newsViewModel)
        }

    }
}

private fun decodeArg(value: String?): String {
    return value?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.name()) } ?: ""
}
