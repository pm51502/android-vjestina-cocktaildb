package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.navigation.navigateToScreen
import com.example.cocktaildb.ui.screens.shared.components.GridLayout
import com.example.cocktaildb.utils.QueryType
import com.example.cocktaildb.utils.homeCategories

@Composable
fun HomeScreen(navController: NavController) {

    val onItemClick = { queryParam: String, queryType: QueryType ->
        val queryString = when (queryType) {
            is QueryType.Category -> "category=$queryParam"
            is QueryType.Ingredient -> "ingredient=$queryParam"
            is QueryType.CocktailId -> "id=$queryParam"
        }

        navigateToScreen(
            navController = navController,
            route = "${RootScreen.Cocktails.route}?$queryString"
        )
    }

    LazyColumn {
        item {
            GridLayout(
                title = "What's popular",
                items = homeCategories.subList(0, 2),
                onItemClick = onItemClick
            )
        }

        item {
            GridLayout(
                title = "Explore",
                items = homeCategories.subList(2, 3),
                onItemClick = onItemClick
            )
        }

        item {
            GridLayout(
                title = "Popular ingredients",
                items = homeCategories.subList(3, 7),
                onItemClick = onItemClick
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
