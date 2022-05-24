package com.example.cocktaildb.utils

import com.example.cocktaildb.R

sealed class QueryType {
    object Category: QueryType()
    object Ingredient: QueryType()
    object CocktailId: QueryType()
}

data class ItemViewState(
    val painterId: Int,
    val title: String,
    val query: String,
    val queryType: QueryType
)

val homeCategories = listOf(
    ItemViewState(
        painterId = R.drawable.popular,
        title = "Popular",
        query = "Popular",
        queryType = QueryType.Category
    ),
    ItemViewState(
        painterId = R.drawable.latest,
        title = "Latest",
        query = "Latest",
        queryType = QueryType.Category
    ),
    ItemViewState(
        painterId = R.drawable.random,
        title = "Random",
        query = "Random",
        queryType = QueryType.Category
    ),
    ItemViewState(
        painterId = R.drawable.vodka,
        title = "Vodka",
        query = "Vodka",
        queryType = QueryType.Ingredient
    ),
    ItemViewState(
        painterId = R.drawable.gin,
        title = "Gin",
        query = "Gin",
        queryType = QueryType.Ingredient
    ),
    ItemViewState(
        painterId = R.drawable.rum,
        title = "Rum",
        query = "Rum",
        queryType = QueryType.Ingredient
    ),
    ItemViewState(
        painterId = R.drawable.tequila,
        title = "Tequila",
        query = "Tequila",
        queryType = QueryType.Ingredient
    )
)

val cocktailsList = listOf(
    ItemViewState(
        painterId = R.drawable.popular,
        title = "Cocktail 1",
        query = "1",
        queryType = QueryType.CocktailId
    ),
    ItemViewState(
        painterId = R.drawable.popular,
        title = "Cocktail 2",
        query = "2",
        queryType = QueryType.CocktailId
    ),
    ItemViewState(
        painterId = R.drawable.popular,
        title = "Cocktail 3",
        query = "3",
        queryType = QueryType.CocktailId
    ),
    ItemViewState(
        painterId = R.drawable.popular,
        title = "Cocktail 4",
        query = "4",
        queryType = QueryType.CocktailId
    ),
    ItemViewState(
        painterId = R.drawable.popular,
        title = "Cocktail 5",
        query = "5",
        queryType = QueryType.CocktailId
    )
)
