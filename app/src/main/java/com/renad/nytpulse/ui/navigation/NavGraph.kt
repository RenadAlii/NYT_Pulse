package com.renad.nytpulse.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.renad.nytpulse.domain.enities.Article
import com.renad.nytpulse.ui.articles.articles.MostViewedArticlesScreen
import com.renad.nytpulse.ui.articles.details.ArticleDetailsScreen

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
) {
    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = Screen.MostViewedArticles,
        modifier = modifier,
    ) {
        composable<Screen.MostViewedArticles> {
            BackHandler {
                finishActivity()
            }
            MostViewedArticlesScreen(
                navigateToDetailsScreen = actions.detailsScreen,
            )
        }
        composable<Screen.Details>
        { backStackEntry ->
            val id = backStackEntry.toRoute<Screen.Details>().id
            val title = backStackEntry.toRoute<Screen.Details>().title
            val abstract = backStackEntry.toRoute<Screen.Details>().abstract
            val byline = backStackEntry.toRoute<Screen.Details>().byline
            val imageUrl = backStackEntry.toRoute<Screen.Details>().imageUrl
            ArticleDetailsScreen(Article(id, title, abstract, byline, imageUrl))
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val onPopBackStack: () -> Unit = {
        navController.popBackStack()
    }
    val detailsScreen = { article: Article ->
        navController.navigate(
            Screen.Details(
                article.id,
                article.title,
                article.abstract,
                article.byline,
                article.imageUrl
            )
        )
    }
}
