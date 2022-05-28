package com.example.cocktaildb.utils

import com.example.cocktaildb.R

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
    val imageUrl: String?,
    val cocktailName: String,
    val isFavorite: Boolean
)
