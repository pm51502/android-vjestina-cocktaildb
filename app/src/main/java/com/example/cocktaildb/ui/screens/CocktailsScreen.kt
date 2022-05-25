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
import com.example.cocktaildb.utils.CocktailViewState
import com.example.cocktaildb.utils.cocktailsList
import androidx.compose.runtime.*

@Composable
fun CocktailsScreen(
    modifier: Modifier = Modifier,
    rootNavController: NavController,
    category: String?,
    ingredient: String?
) {
    var allCocktails by remember {
        mutableStateOf(cocktailsList)
    }

    val onCocktailClick = { cocktailId: Int ->
        navigateToScreen(
            navController = rootNavController,
            route = "${RootScreen.Details.route}/$cocktailId"
        )
    }

    val onFavoriteClick = { cocktail: CocktailViewState -> //CocktailView later
        val index = allCocktails.indexOf(cocktail)
        val updatedCocktail = cocktail.copy(isFavorite = cocktail.isFavorite.not())

        allCocktails = allCocktails.toMutableList().apply { set(index, updatedCocktail) }
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
        var cocktailsType = ""
        if (category != null && ingredient != null) {
            cocktailsType =
                if (category != "none") "$category cocktails" else "$ingredient cocktails"
        }

        LazyColumn {
            item {
                CocktailsLayout(
                    title = cocktailsType,
                    cocktails = allCocktails,
                    onCocktailClick = onCocktailClick,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}
