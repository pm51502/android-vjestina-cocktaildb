package com.example.cocktaildb.ui.screens.shared.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.cocktaildb.R
import com.example.cocktaildb.data.Cocktail
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
    onFavoriteClick: (cocktail: Cocktail) -> Unit
) {
    Column {
        ContentTitle(text = title)

        /*for (i in 0 until cocktails.size-1 step 2) {
            item {
                CocktailsRow(
                    item1 = cocktails[i],
                    item2 = cocktails[i+1],
                    onCocktailClick = onCocktailClick,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }*/

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

/*
@Composable
fun CocktailsRow(
    modifier: Modifier = Modifier,
    item1: CocktailViewState?,
    item2: CocktailViewState?,
    onCocktailClick: (cocktailId: Int) -> Unit,
    onFavoriteClick: (cocktail: Cocktail) -> Unit
) {
    LazyRow(
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.padding_md),
            top = dimensionResource(id = R.dimen.padding_sm)
        )
    ) {
        if (item1 != null)
            item {
                CocktailCard(
                    cocktail = item1,
                    onCocktailClick = onCocktailClick,
                    onFavoriteClick = onFavoriteClick
                )
            }

        item {
            Spacer(modifier = modifier.width(dimensionResource(id = R.dimen.spacer_xl)))
        }

        item {
            if (item2 != null)
                CocktailCard(
                    cocktail = item2,
                    onCocktailClick = onCocktailClick,
                    onFavoriteClick = onFavoriteClick
                )
        }
    }
}*/
