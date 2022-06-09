package com.example.cocktaildb.ui.screens.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.cocktaildb.R
import com.example.cocktaildb.utils.CocktailViewState

@Composable
fun CocktailCard(
    modifier: Modifier = Modifier,
    cocktail: CocktailViewState,
    onCocktailClick: (cocktailId: Int) -> Unit,
    onFavoriteClick: (cocktail: CocktailViewState) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.45f)
            .clickable {
                onCocktailClick.invoke(cocktail.id)
            }
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Box(modifier = modifier.height(200.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(model = cocktail.imageUrl),
                    contentDescription = stringResource(id = R.string.image_card),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .matchParentSize()
                        .drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = size.height / 3,
                                endY = size.height
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(
                                    gradient,
                                    blendMode = BlendMode.Multiply
                                )
                            }
                        }
                )
                FavoriteButton(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.small_spacing),
                        top = dimensionResource(id = R.dimen.small_spacing)
                    ),
                    item = cocktail,
                    onFavoriteClick = onFavoriteClick
                )
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = cocktail.cocktailName,
                        style = MaterialTheme.typography.h4
                    )
                }
            }
        }
    }
}
