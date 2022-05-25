package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.navigation.navigateToScreen
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.example.cocktaildb.R
import com.example.cocktaildb.ui.screens.shared.components.CocktailsLayout
import com.example.cocktaildb.utils.CocktailViewState
import com.example.cocktaildb.utils.cocktailsList

@Composable
fun FavoritesScreen(navController: NavController) {
    var favoriteCocktails by remember {
        mutableStateOf(cocktailsList.filter { it.isFavorite })
    }

    val onCocktailClick = { cocktailId: Int ->
        navigateToScreen(
            navController = navController,
            route = "${RootScreen.Details.route}/$cocktailId"
        )
    }

    val onFavoriteClick = { cocktail: CocktailViewState -> //CocktailView later
        /*val index = favoriteCocktails.indexOf(cocktail)
        val updatedCocktail = cocktail.copy(isFavorite = cocktail.isFavorite.not())
        allCocktails = allCocktails.toMutableList().apply { set(index, updatedCocktail) }*/
    }

    LazyColumn {
        item {
            CocktailsLayout(
                title = stringResource(id = R.string.favorites),
                cocktails = favoriteCocktails,
                onCocktailClick = onCocktailClick,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}
