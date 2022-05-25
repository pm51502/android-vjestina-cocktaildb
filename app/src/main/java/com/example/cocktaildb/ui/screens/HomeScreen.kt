package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.navigation.navigateToScreen
import com.example.cocktaildb.ui.screens.shared.components.CategoriesLayout
import com.example.cocktaildb.utils.QueryType
import com.example.cocktaildb.utils.homeCategories

@Composable
fun HomeScreen(navController: NavController) {

    val onCategoryClick = { queryParam: String, queryType: QueryType ->
        val queryString = when (queryType) {
            is QueryType.Category -> "category=$queryParam"
            is QueryType.Ingredient -> "ingredient=$queryParam"
        }

        navigateToScreen(
            navController = navController,
            route = "${RootScreen.Cocktails.route}?$queryString"
        )
    }

    LazyColumn {
        item {
            CategoriesLayout(
                title = "What's popular",
                categories = homeCategories.subList(0, 2),
                onCategoryClick = onCategoryClick
            )
        }

        item {
            CategoriesLayout(
                title = "Explore",
                categories = homeCategories.subList(2, 3),
                onCategoryClick = onCategoryClick
            )
        }

        item {
            CategoriesLayout(
                title = "Popular ingredients",
                categories = homeCategories.subList(3, 7),
                onCategoryClick = onCategoryClick
            )
        }
    }
}

/*Text(text = "Home screen")
Button(onClick = { navigateToScreen(
    navController = navController,
    route = "${RootScreen.Details.route}/123"
) }) {
    Text(text = "Go to details screen")
}*/
