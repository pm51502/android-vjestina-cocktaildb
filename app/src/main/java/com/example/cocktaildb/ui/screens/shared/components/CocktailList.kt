package com.example.cocktaildb.ui.screens.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.navigation.navigateToScreen
import com.example.cocktaildb.viewmodels.SearchViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun CocktailList(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: MutableState<TextFieldValue>
) {
    val searchViewModel by viewModel<SearchViewModel>()
    searchViewModel.refreshSearchResults(state.value.text)
    val cocktails =
        searchViewModel.cocktailSearchSharedFlow.collectAsState(initial = emptyList()).value

    val onItemClick = { cocktailId: Int ->
        navigateToScreen(
            navController = navController,
            route = "${RootScreen.Details.route}/$cocktailId"
        )
    }

    Column(modifier = modifier.fillMaxWidth()) {
        cocktails.forEach {
            CocktailListItem(
                cocktail = it,
                onItemClick = onItemClick
            )
        }
    }
}
