package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.navigation.navigateToScreen
import com.example.cocktaildb.ui.screens.shared.components.ContentTitle
import com.example.cocktaildb.ui.screens.shared.components.GridLayout
import com.example.cocktaildb.ui.screens.shared.components.TopBar
import com.example.cocktaildb.utils.QueryType
import com.example.cocktaildb.utils.cocktailsList

@Composable
fun CocktailsScreen(
    modifier: Modifier = Modifier,
    rootNavController: NavController,
    category: String?,
    ingredient: String?,
    id: String?
) {
    val onItemClick = { queryParam: String, queryType: QueryType ->
        var cocktailId = 1
        if (queryType is QueryType.CocktailId)
            cocktailId = queryParam.toInt()

        navigateToScreen(
            navController = rootNavController,
            route = "${RootScreen.Details.route}/$cocktailId"
        )
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
        var title = ""
        if (category != null && ingredient != null) {
            title = if (category != "none") "$category cocktails" else "$ingredient cocktails"
        }

        LazyColumn {
            item {
                GridLayout(
                    title = title,
                    items = cocktailsList,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

/*item {
    Column {
        Text(text = "Cocktails screen")
        if (category != null) Text(text = "Category: $category")
        if (ingredient != null) Text(text = "Ingredient: $ingredient")
    }
}*/
