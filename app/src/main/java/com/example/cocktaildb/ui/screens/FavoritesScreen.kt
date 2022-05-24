package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.navigation.navigateToScreen
import com.example.cocktaildb.ui.screens.shared.components.ContentTitle

@Composable
fun FavoritesScreen(navController: NavController) {
    LazyColumn {
        item {
            ContentTitle(text = "Favorites")
        }
    }

}

/*Column {
    Text(text = "Favorites screen")
    Button(onClick = { navigateToScreen(
        navController = navController,
        route = "${RootScreen.Cocktails.route}?category=popular&ingredient=gin"
    ) }) {
        Text(text = "Go to cocktails screen")
    }
}*/
