package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktaildb.R
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.screens.shared.components.ContentTitle
import com.example.cocktaildb.ui.screens.shared.components.TopBar
import com.example.cocktaildb.utils.getCocktailDetails

data class CocktailDetailsViewState(
    val id: Int,
    val painterId: Int,
    val name: String,
    val category: String,
    val alcoholic: String,
    val ingredients: List<Ingredient>,
    val instructions: String,
    val tags: List<Tag>,
    //imageUrl: String?
)

data class Ingredient(
    val name: String,
    val measure: String
)

data class Tag(
    val name: String
)

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    rootNavController: NavController,
    cocktailId: Int?
) {
    val cocktailDetails = getCocktailDetails(cocktailId)

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
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.details_image_height))
                ) {
                    Image(
                        painter = painterResource(id = cocktailDetails.painterId),
                        contentDescription = stringResource(id = R.string.details_image),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .matchParentSize()
                            .drawWithCache {
                                val gradient = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = size.height / 3,
                                    endY = size.height
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(gradient, blendMode = BlendMode.Multiply)
                                }
                            }
                    )

                    Column(
                        modifier = modifier.padding(
                            start = dimensionResource(id = R.dimen.details_column_start_padding),
                            top = dimensionResource(id = R.dimen.details_column_top_padding)
                        )
                    ) {
                        Text(
                            text = cocktailDetails.name,
                            style = MaterialTheme.typography.h2
                        )

                        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacer_m)))

                        Text(
                            text = cocktailDetails.category,
                            style = MaterialTheme.typography.subtitle1
                        )

                        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacer_m)))

                        Text(
                            text = cocktailDetails.alcoholic,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }

            item {
                Column {
                    ContentTitle(text = stringResource(id = R.string.ingredients))
                    IngredientsList(ingredients = cocktailDetails.ingredients)
                }
            }

            item {
                Column {
                    ContentTitle(text = stringResource(id = R.string.instructions))
                    Text(
                        text = cocktailDetails.instructions,
                        modifier = modifier.padding(
                            start = dimensionResource(id = R.dimen.padding_md),
                            top = dimensionResource(id = R.dimen.padding_xsm),
                            end = dimensionResource(id = R.dimen.padding_md)
                        )
                    )
                }
            }

            item {
                Column {
                    ContentTitle(text = stringResource(id = R.string.tags))
                    Text(
                        text = cocktailDetails.tags.joinToString { it.name },
                        modifier = modifier.padding(
                            start = dimensionResource(id = R.dimen.padding_md),
                            top = dimensionResource(id = R.dimen.padding_xsm),
                            end = dimensionResource(id = R.dimen.padding_md)
                        ),
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        }
    }
}

@Composable
fun IngredientsList(
    modifier: Modifier = Modifier,
    ingredients: List<Ingredient>
) {
    Column(
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.padding_md),
            top = dimensionResource(id = R.dimen.padding_xsm),
            end = dimensionResource(id = R.dimen.padding_md)
        )
    ) {
        ingredients.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(
                    modifier = modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .size(6.dp)
                ) {
                    drawCircle(Color(0xFF0C002A))
                }
                Text(
                    text = "${it.name} (${it.measure})",
                    style = MaterialTheme.typography.h4,
                    color = Color(0xFF0C002A)
                )
            }
        }
    }
}
