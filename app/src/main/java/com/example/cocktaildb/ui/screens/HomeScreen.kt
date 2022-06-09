package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.navigation.navigateToScreen
import com.example.cocktaildb.ui.screens.shared.components.CategoriesLayout
import com.example.cocktaildb.ui.screens.shared.components.CocktailList
import com.example.cocktaildb.ui.screens.shared.components.SearchView
import com.example.cocktaildb.utils.ConnectionState
import com.example.cocktaildb.utils.QueryType
import com.example.cocktaildb.utils.connectivityState
import com.example.cocktaildb.utils.homeCategories

@Composable
fun HomeScreen(navController: NavController) {

    val textState = remember { mutableStateOf(TextFieldValue()) }
    val connection by connectivityState()
    val isConnected = connection == ConnectionState.Available

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

    if (!isConnected) {
        OfflineScreen()
    } else {
        LazyColumn {
            item {
                SearchView(state = textState)
            }

            if (textState.value.text != "") {
                item {
                    CocktailList(
                        navController = navController,
                        state = textState
                    )
                }
            }

            item {
                CategoriesLayout(
                    title = "Popular cocktails",
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
}
