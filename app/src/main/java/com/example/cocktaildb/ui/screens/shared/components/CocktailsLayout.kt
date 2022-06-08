package com.example.cocktaildb.ui.screens.shared.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.cocktaildb.R
import com.example.cocktaildb.network.Cocktail
import com.example.cocktaildb.utils.CocktailViewState
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun CocktailsLayout(
    modifier: Modifier = Modifier,
    title: String,
    cocktails: List<CocktailViewState>,
    onCocktailClick: (cocktailId: Int) -> Unit,
    onFavoriteClick: (cocktail: CocktailViewState) -> Unit
) {
    Column {
        ContentTitle(text = title)

        FlowRow(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_md),
                top = dimensionResource(id = R.dimen.padding_sm)
            ),
            mainAxisSize = SizeMode.Wrap,
            mainAxisAlignment = FlowMainAxisAlignment.Start,
            mainAxisSpacing = dimensionResource(id = R.dimen.padding_md),
            crossAxisSpacing = dimensionResource(id = R.dimen.padding_md)
        ) {
            cocktails.forEach {
                CocktailCard(
                    cocktail = it,
                    onCocktailClick = onCocktailClick,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }

        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacer_m)))
    }
}
