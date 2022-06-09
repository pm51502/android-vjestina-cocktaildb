package com.example.cocktaildb.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cocktaildb.R
import com.example.cocktaildb.ui.navigation.RootScreen
import com.example.cocktaildb.ui.screens.shared.components.ContentTitle
import com.example.cocktaildb.ui.screens.shared.components.FavoriteButton
import com.example.cocktaildb.ui.screens.shared.components.TopBar
import com.example.cocktaildb.utils.CocktailViewState
import com.example.cocktaildb.utils.toCocktailViewState
import com.example.cocktaildb.utils.toDbCocktail
import com.example.cocktaildb.viewmodels.DetailsViewModel
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

data class CocktailDetailsViewState(
    val id: Int = 0,
    val imageUrl: String? = "",
    val name: String = "",
    val category: String = "",
    val alcoholic: String = "",
    val ingredients: List<String> = emptyList(),
    val measures: List<String> = emptyList(),
    val instructions: String? = "",
    val tags: List<String>? = emptyList(),
    val isFavorite: Boolean = false
)

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    rootNavController: NavController,
    cocktailId: Int?
) {
    val detailsViewModel by viewModel<DetailsViewModel> { parametersOf(cocktailId) }
    val cocktailDetails = detailsViewModel.cocktailDetailsStateFlow.collectAsState().value

    val onFavoriteClick = { cocktail: CocktailViewState ->
        if (!cocktail.isFavorite)
            detailsViewModel.deleteFavoriteCocktail(cocktailId = cocktail.id)
        else
            detailsViewModel.insertFavoriteCocktail(cocktail = cocktail.toDbCocktail())
    }

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
                        painter = rememberAsyncImagePainter(model = cocktailDetails.imageUrl),
                        //painterResource(id = cocktailDetails.painterId),
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

                        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacer_m)))

                        FavoriteButton(
                            item = cocktailDetails.toCocktailViewState(),
                            onFavoriteClick = onFavoriteClick
                        )
                    }
                }
            }

            item {
                Column {
                    ContentTitle(text = stringResource(id = R.string.ingredients))
                    IngredientsList(
                        ingredients = cocktailDetails.ingredients,
                        measures = cocktailDetails.measures
                    )
                }
            }

            if (cocktailDetails.instructions != null) {
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
            }

            if (cocktailDetails.tags != null) {
                item {
                    Column {
                        ContentTitle(text = stringResource(id = R.string.tags))
                        Text(
                            text = cocktailDetails.tags.joinToString { it },
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
}

@Composable
fun IngredientsList(
    modifier: Modifier = Modifier,
    ingredients: List<String>,
    measures: List<String>
) {
    Column(
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.padding_md),
            top = dimensionResource(id = R.dimen.padding_xsm),
            end = dimensionResource(id = R.dimen.padding_md)
        )
    ) {

        ingredients.zip(measures) { ingredient, measure ->
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
                    text = "$ingredient ($measure)",
                    style = MaterialTheme.typography.h4,
                    color = Color(0xFF0C002A)
                )
            }
        }
    }
}
