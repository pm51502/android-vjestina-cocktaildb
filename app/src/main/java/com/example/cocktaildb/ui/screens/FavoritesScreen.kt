package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.navigation.navigateToScreen
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.example.cocktaildb.R
import com.example.cocktaildb.data.Cocktail
import com.example.cocktaildb.ui.screens.shared.components.CocktailsLayout
import com.example.cocktaildb.viewmodels.FavoritesViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun FavoritesScreen(navController: NavController) {
    val favoritesViewModel by viewModel<FavoritesViewModel>()
    val favoriteCocktails =
        favoritesViewModel.favoriteCocktailsStateFlow.collectAsState(initial = emptyList()).value

    val onCocktailClick = { cocktailId: Int ->
        navigateToScreen(
            navController = navController,
            route = "${RootScreen.Details.route}/$cocktailId"
        )
    }

    val onFavoriteClick = { cocktail: Cocktail ->
        favoritesViewModel.toggleFavorite(cocktail = cocktail)
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
