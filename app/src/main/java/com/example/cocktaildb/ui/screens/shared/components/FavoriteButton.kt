package com.example.cocktaildb.ui.screens.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.cocktaildb.R
import com.example.cocktaildb.data.Cocktail
import com.example.cocktaildb.data.toCocktail
import com.example.cocktaildb.utils.CocktailViewState

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    item: CocktailViewState,
    onFavoriteClick: (cocktail: Cocktail) -> Unit
) {
    Image(
        painter = painterResource(id = if (item.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite),
        contentDescription = null,
        modifier = modifier
            .clickable {
                onFavoriteClick.invoke(item.toCocktail())
            }
            .size(dimensionResource(id = R.dimen.large_spacing))
            .background(
                color = colorResource(id = R.color.dark_purple_60),
                CircleShape
            )
            .padding(dimensionResource(id = R.dimen.small_spacing))
    )
}
