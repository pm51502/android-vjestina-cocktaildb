package com.example.cocktaildb.ui.screens.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.cocktaildb.R
import com.example.cocktaildb.network.Cocktail
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.navigation.navigateToScreen
import com.example.cocktaildb.viewmodels.SearchViewModel
import org.koin.androidx.compose.viewModel
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun CocktailList(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: MutableState<TextFieldValue>
) {
    val searchViewModel by viewModel<SearchViewModel>()
    val cocktails =
        searchViewModel.cocktailSearchSharedFlow.collectAsState(initial = emptyList()).value
    var filteredCocktails: List<Cocktail>

    val onItemClick = { cocktailId: Int ->
        navigateToScreen(
            navController = navController,
            route = "${RootScreen.Details.route}/$cocktailId"
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.padding_sm))
    ) {
        val searchedText = state.value.text
        filteredCocktails = if (searchedText.isEmpty()) {
            cocktails
        } else {
            val resultList = ArrayList<Cocktail>()
            for (cocktail in cocktails) {
                if (cocktail.strDrink.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(cocktail)
                }
            }
            resultList
        }

        filteredCocktails.forEach {
            CocktailListItem(
                cocktail = it,
                onItemClick = onItemClick
            )
        }
    }
}
