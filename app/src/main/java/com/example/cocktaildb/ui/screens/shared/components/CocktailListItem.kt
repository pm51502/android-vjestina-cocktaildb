package com.example.cocktaildb.ui.screens.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cocktaildb.R
import com.example.cocktaildb.network.Cocktail

@Composable
fun CocktailListItem(
    modifier: Modifier = Modifier,
    cocktail: Cocktail,
    onItemClick: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .clickable(onClick = { onItemClick.invoke(cocktail.idDrink) })
            .background(colorResource(id = R.color.dark_purple))
            .height(dimensionResource(id = R.dimen.cocktail_list_item_height))
            .fillMaxWidth()
            .padding(PaddingValues(
                dimensionResource(id = R.dimen.cocktail_list_item_padding_h),
                dimensionResource(id = R.dimen.cocktail_list_item_padding_v)
            ))
    ) {
        Text(
            text = cocktail.strDrink,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}
