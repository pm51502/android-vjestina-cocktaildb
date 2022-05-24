package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.screens.shared.components.TopBar

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    rootNavController: NavController,
    cocktailId: Int?
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                currentRoute = RootScreen.Details.route,
                rootNavController = rootNavController
            )
        }
    ) {
        LazyColumn {
            item {
                Column {
                    Text(text = "Details screen")
                    Text(text = "Cocktail id: $cocktailId")
                }
            }
        }
    }
}
