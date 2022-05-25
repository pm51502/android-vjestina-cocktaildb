package com.example.cocktaildb.utils

import com.example.cocktaildb.R
import com.example.cocktaildb.ui.screens.CocktailDetailsViewState
import com.example.cocktaildb.ui.screens.Ingredient
import com.example.cocktaildb.ui.screens.Tag

sealed class QueryType {
    object Category : QueryType()
    object Ingredient : QueryType()
}

data class CategoryViewState(
    val painterId: Int,
    val categoryName: String,
    val query: String,
    val queryType: QueryType
)

val homeCategories = listOf(
    CategoryViewState(
        painterId = R.drawable.popular,
        categoryName = "Popular",
        query = "Popular",
        queryType = QueryType.Category
    ),
    CategoryViewState(
        painterId = R.drawable.latest,
        categoryName = "Latest",
        query = "Latest",
        queryType = QueryType.Category
    ),
    CategoryViewState(
        painterId = R.drawable.random,
        categoryName = "Random",
        query = "Random",
        queryType = QueryType.Category
    ),
    CategoryViewState(
        painterId = R.drawable.vodka,
        categoryName = "Vodka",
        query = "Vodka",
        queryType = QueryType.Ingredient
    ),
    CategoryViewState(
        painterId = R.drawable.gin,
        categoryName = "Gin",
        query = "Gin",
        queryType = QueryType.Ingredient
    ),
    CategoryViewState(
        painterId = R.drawable.rum,
        categoryName = "Rum",
        query = "Rum",
        queryType = QueryType.Ingredient
    ),
    CategoryViewState(
        painterId = R.drawable.tequila,
        categoryName = "Tequila",
        query = "Tequila",
        queryType = QueryType.Ingredient
    )
)

data class CocktailViewState(
    val id: Int,
    val painterId: Int,
    val cocktailName: String,
    val isFavorite: Boolean,
    //val imageUrl: String?
)

val cocktailsList = listOf(
    CocktailViewState(
        id = 1,
        painterId = R.drawable.popular,
        cocktailName = "Cocktail 1",
        isFavorite = false
    ),
    CocktailViewState(
        id = 2,
        painterId = R.drawable.popular,
        cocktailName = "Cocktail 2",
        isFavorite = false
    ),
    CocktailViewState(
        id = 3,
        painterId = R.drawable.popular,
        cocktailName = "Cocktail 3",
        isFavorite = false
    ),
    CocktailViewState(
        id = 4,
        painterId = R.drawable.popular,
        cocktailName = "Cocktail 4",
        isFavorite = false
    ),
    CocktailViewState(
        id = 5,
        painterId = R.drawable.popular,
        cocktailName = "Cocktail 5",
        isFavorite = true
    ),
    CocktailViewState(
        id = 6,
        painterId = R.drawable.popular,
        cocktailName = "Cocktail 6",
        isFavorite = true
    ),
    CocktailViewState(
        id = 7,
        painterId = R.drawable.popular,
        cocktailName = "Cocktail 7",
        isFavorite = true
    )
)

fun getCocktailDetails(id: Int?) = CocktailDetailsViewState(
    id = 1,
    painterId = R.drawable.popular,
    name = "Old Fashioned",
    category = "Cocktail",
    alcoholic = "Alcoholic",
    ingredients = listOf(
        Ingredient(
            name = "Bourbon",
            measure = "4.5 cL"
        ),
        Ingredient(
            name = "Angostura bitter",
            measure = "2 dashes"
        ),
        Ingredient(
            name = "Sugar",
            measure = "1 cube"
        ),
        Ingredient(
            name = "Water",
            measure = "dash"
        ),
    ),
    instructions = "Place sugar cube in old fashioned glass and saturate with bitters, add a dash of plain water. Muddle until dissolved.\r\nFill the glass with ice cubes and add whiskey.\r\n\r\nGarnish with orange twist, and a cocktail cherry.",
    tags = listOf(
        Tag(name = "IBA"),
        Tag(name = "Classic"),
        Tag(name = "Alcoholic"),
        Tag(name = "Expensive"),
        Tag(name = "Savory")
    )
)
