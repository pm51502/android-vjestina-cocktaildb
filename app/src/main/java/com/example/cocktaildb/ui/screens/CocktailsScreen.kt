package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.navigation.navigateToScreen
import com.example.cocktaildb.ui.screens.shared.components.CocktailsLayout
import com.example.cocktaildb.ui.screens.shared.components.TopBar
import androidx.compose.runtime.*
import com.example.cocktaildb.data.Cocktail
import com.example.cocktaildb.viewmodels.CocktailsViewModel
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CocktailsScreen(
    modifier: Modifier = Modifier,
    rootNavController: NavController,
    category: String?,
    ingredient: String?
) {
    var cocktailsType = ""
    if (category != null && ingredient != null) {
        cocktailsType =
            if (category != "none") category else ingredient
    }

    val cocktailsViewModel by viewModel<CocktailsViewModel> { parametersOf(cocktailsType) }
    val categoryCocktails = cocktailsViewModel.categoryCocktailsStateFlow.collectAsState().value

    val onCocktailClick = { cocktailId: Int ->
        navigateToScreen(
            navController = rootNavController,
            route = "${RootScreen.Details.route}/$cocktailId"
        )
    }

    val onFavoriteClick = { cocktail: Cocktail ->
        cocktailsViewModel.toggleFavorite(cocktail = cocktail)
    }

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                currentRoute = RootScreen.Cocktails.route,
                rootNavController = rootNavController
            )
        }
    ) {
        LazyColumn {
            item {
                CocktailsLayout(
                    title = "$cocktailsType cocktails",
                    cocktails = categoryCocktails,
                    onCocktailClick = onCocktailClick,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}
